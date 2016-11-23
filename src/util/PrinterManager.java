/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.Resource;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 *
 * @author andrea
 */
public class PrinterManager {

    private freemarker.template.Configuration configuration;
    private static String CHARSET= "ISO-8859-1";
    private Resource templatePath;

    
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    PrinterManager() {
    }

    public static ByteArrayOutputStream html2Pdf(String html) throws Throwable {
        //html=Strings.escapeMarkup(html, false, true);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        org.w3c.dom.Document doc = builder.parse(new ByteArrayInputStream(html.getBytes(CHARSET)));
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(doc, null);
        renderer.layout();
        renderer.createPDF(os);
        os.close();
        return os;
    }

    public ByteArrayOutputStream printAsPdf(String template, Map model) {       
        try {
            return html2Pdf( print(template,model).toString(CHARSET) );
        } catch (Throwable e) {
            e.printStackTrace();
            // System.out.println("ciao");
        }
        try {
            return html2Pdf("<html><head><title>Errore</title></head><body>Template " +template+" rotto applicato su "+model+"</body></html>");
        }catch(Throwable ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ByteArrayOutputStream print(String template, Map<String, Object> model) {
        String CHARSET= "ISO-8859-1";
         try {
            //WebApplication wa= ((WebApplication)Application.get());
            //String realPath=wa.getServletContext().getRealPath("/img");
            String realPath=new File(".").getAbsolutePath();
            model.put("imageBaseDir", new File(realPath).toURI().toURL().getPath());
        } catch (Exception e) {
            model.put("imageBaseDir", "/tmp" );
            //e.printStackTrace();
        }
        try {
            getConfiguration().setObjectWrapper(new DefaultObjectWrapper());
            Template t = getConfiguration().getTemplate(template, CHARSET);            
            String html = processTemplateIntoString(t, model);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            baos.write(html.getBytes());
            return baos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static String processTemplateIntoString(Template template, Object model)
			throws IOException, TemplateException {
		StringWriter result = new StringWriter();
		template.process(model, result);
		return result.toString();
	}

    public void silentPrint(byte []bytes) throws Exception {
            Document document = new Document();
        FileOutputStream fos = new FileOutputStream("SilentPrint.pdf");

         try {
              PdfWriter writer = PdfWriter.getInstance(document, fos);
              document.open();
              writer.addJavaScript("this.print(false);", false);
              writer.addJavaScript("this.print({bUI: false, bSilent: true, bShrinkToFit: true});",false);
              document.add(new Chunk("Silent Auto Print"));
              document.close();
        } catch (DocumentException e) {
                e.printStackTrace();
        }
    }
    /*
    private ByteArrayOutputStream makeHtml(String templateName, PianoTerapeutico record, SoleWebUser user) {
        Configuration config = getConfiguration();

        // setup modello
        Map templateInput = new HashMap();
        templateInput.put("record", record);
        templateInput.put("farmacista", user.getName() + " " + user.getSurname());
        templateInput.put("imageBaseDir", new File(imageDir));
        Map farmaco = new HashMap();
        List<Map<String, String>> farmaci = new ArrayList<Map<String, String>>();
        Map farmaciIte = (Map) record.get("SARPIT_REP_Farmaci");
        int i = 1;
        Map listaErogati = (Map) record.get("erogato");
        while (farmaciIte.get(i + "2%Codice ATC%") != null) {
            farmaco.put("Codice", farmaciIte.get(i + "2%Codice ATC%"));
            farmaco.put("Farmaco", farmaciIte.get(i + "1%Farmaco Prescritto%"));
            farmaco.put("Dosaggio", farmaciIte.get(i + "3%Dosaggio%"));
            farmaco.put("Forma", farmaciIte.get(i + "4%Forma%"));
            farmaco.put("Posologia", farmaciIte.get(i + "5%Posologia%"));
            if (ssn.getAttribute("FROM_SATEROG") != null) {
                Map tmp = (Map) ssn.getAttribute("FROM_SATEROG");
                for (int ite = 1;;) {
                    if (tmp.get(ite) != null) {
                        Map minsan = (Map) tmp.get(ite);
                        if (minsan.get("ATCCode").equals(farmaciIte.get(i + "2%Codice ATC%"))) {
                            farmaco.put("Minsan10", minsan.get("descript").toString());
                            farmaco.put("Quantita", minsan.get("refills"));
                            farmaco.put("Data", minsan.get("data"));
                            farmaco.put("CodiceStrutturaHSP", (String) minsan.get("hsp"));
                            break;
                        }
                        ite++;
                    } else {
                        break;
                    }
                }
            }
            farmaci.add(farmaco);
            farmaco = new HashMap();
            i++;
        }
        templateInput.put("farmaci", farmaci);

        Map datiValidazione = new HashMap();
        farmaciIte = (Map) record.get("datidivalidazione");
        if (farmaciIte.get("2%Medico che Sospende il Piano") != null) {
            datiValidazione.put("Medico", farmaciIte.get("2%Medico che Sospende il Piano"));
            datiValidazione.put("Motivo", farmaciIte.get("2%Motivo della Sospensione"));
            datiValidazione.put("Data", farmaciIte.get("2%Data della Sospensione"));
            templateInput.put("sospeso", datiValidazione);
            datiValidazione = new HashMap();
        }
        if (farmaciIte.get("1%Motivo del Rifiuto") != null) {
            datiValidazione.put("Farmacista", farmaciIte.get("1%Farmacista che Rifiuta il piano"));
            datiValidazione.put("Motivo", farmaciIte.get("1%Motivo del Rifiuto"));
            datiValidazione.put("Data", farmaciIte.get("1%Data del Rifiuto"));
            templateInput.put("rifiuto", datiValidazione);
            datiValidazione = new HashMap();
        } else if (farmaciIte.get("1%Farmacista validatore") != null) {
            datiValidazione.put("Farmacista", farmaciIte.get("1%Farmacista validatore"));
            datiValidazione.put("Data", farmaciIte.get("1%Data della Validazione"));
            datiValidazione.put("SARPIT_OUT_EntererHSPCode", farmaciIte.get("1%SARPIT_OUT_EntererHSPCode"));
            datiValidazione.put("SARPIT_OUT_EntererHSPDescription", farmaciIte.get("1%SARPIT_OUT_EntererHSPDescription"));
            templateInput.put("validazione", datiValidazione);
        }
        if (user.isMMG()) {
            templateInput.put("mmg", "yes");
        }

        // creo html + pdf
        return print(templateName, templateInput);
    }
     
     */
}
