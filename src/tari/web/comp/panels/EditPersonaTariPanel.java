package tari.web.comp.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tari.logic.TariManagerExt;
import tari.modelExt.PersonaTariCompleta;
import web.c.BasePanel;
import web.c.MyAllegato;
import web.c.MyCheckBox;

public class EditPersonaTariPanel extends BasePanel{
	@SpringBean
	TariManagerExt tariManagerExt;
	
	public EditPersonaTariPanel(String id,PersonaTariCompleta pc, boolean onNew) {
		super(id);
		
		Form formAnagrafica = new Form("formAnagrafica"){
			
		};
		add(formAnagrafica);
		
		formAnagrafica.add(new MyCheckBox("isditta", new PropertyModel<>(pc, "personaTari.dittaIndividuale"),  new Model("Ditta") ) {
			@Override
			public void onChange(AjaxRequestTarget target, boolean value) {
				target.add(EditPersonaTariPanel.this);
			}
		});
		formAnagrafica.add(new TextField<String>("nome", new PropertyModel<>(pc, "personaTari.nome")));
		formAnagrafica.add(new TextField<String>("cognome", new PropertyModel<>(pc, "personaTari.cognome")));
		formAnagrafica.add(new TextField<String>("comunenascita", new PropertyModel<>(pc, "personaTari.comunenascita")));
		formAnagrafica.add(new DateTextField("datanascita", new PropertyModel<>(pc, "personaTari.datanascita")));
		formAnagrafica.add(new DateTextField("comuneResidenza", new PropertyModel<>(pc, "residenza.comune")));
		formAnagrafica.add(new DateTextField("viaResidenza", new PropertyModel<>(pc, "residenza.via")));
		formAnagrafica.add(new DateTextField("civicoResidenza", new PropertyModel<>(pc, "residenza.civico")));
		formAnagrafica.add(new DateTextField("capResidenza", new PropertyModel<>(pc, "residenza.cap")));
		formAnagrafica.add(new TextField<String>("cf", new PropertyModel<>(pc, "personaTari.cf")));
		formAnagrafica.add(new DateTextField("recapito", new PropertyModel<>(pc, "personaTari.recapitoTelefonico")));
		formAnagrafica.add(new EmailTextField("email", new PropertyModel<>(pc, "personaTari.email")));
		formAnagrafica.add(new EmailTextField("pec", new PropertyModel<>(pc, "personaTari.pec")));
		
		
		Form formAnagrafica2 = new Form("formAnagrafica2"){
			
			@Override
			public boolean isVisible() {
				return pc.getPersonaTari().getDittaIndividuale()==1;
			}
		};
		add(formAnagrafica2);
		formAnagrafica2.add(new TextField<String>("descrizione", new PropertyModel<>(pc, "personaGiuridica.descrizione")));
		formAnagrafica2.add(new TextField<String>("sedeLegale", new PropertyModel<>(pc, "personaGiuridica.sedeLegale")));
		formAnagrafica2.add(new TextField<String>("provincia", new PropertyModel<>(pc, "personaGiuridica.provincia")));
		formAnagrafica2.add(new TextField<String>("indirizzo", new PropertyModel<>(pc, "personaGiuridica.indirizzo")));
		formAnagrafica2.add(new TextField<String>("numeroCivico", new PropertyModel<>(pc, "personaGiuridica.numeroCivico")));
		formAnagrafica2.add(new TextField<String>("pIva", new PropertyModel<>(pc, "personaGiuridica.pIva")));
		formAnagrafica2.add(new TextField<String>("recapitoTelefonico", new PropertyModel<>(pc, "personaGiuridica.recapitoTelefonico")));
		formAnagrafica2.add(new EmailTextField("emailPG", new PropertyModel<>(pc, "personaGiuridica.email")));
		formAnagrafica2.add(new EmailTextField("pecPG", new PropertyModel<>(pc, "personaGiuridica.pec")));
		
		add(new MyAllegato("allegato1", "Invio del Documento di Identita'", 320, 200, "Invia carta di Identita'" ){
			@Override
			public void onUpload(AjaxRequestTarget target, FileUpload fileInviato) {				
				pc.getCartaIdentita().setData(fileInviato.getBytes());
			}
			@Override
			public byte[] getBytesDownload() {
				return pc.getCartaIdentita().getData();
			}
			@Override
			public String getFileName(){
				return "ci.pdf";
			}
		});
		add(new MyAllegato("allegato2", "Invio della Copia della visura camerale", 320, 200, "Invia copia della visura camerale" ){
			@Override
			public void onUpload(AjaxRequestTarget target, FileUpload fileInviato) {
				pc.getVisuraCamerale().setOperation(pc.OP_INSERT);
				pc.getVisuraCamerale().setData(fileInviato.getBytes());
			}
			@Override
			public byte[] getBytesDownload() {
				return pc.getVisuraCamerale().getData();
			}
			@Override
			public String getFileName(){
				return "visura.pdf";
			}
			@Override
			public boolean isVisible() {
				return formAnagrafica2.isVisible();
			}
		});
		Form formAnagrafica3 = new Form("formAnagrafica3");
		add(formAnagrafica3);
		formAnagrafica3.add(new AjaxButton("salva") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				try {
					tariManagerExt.savePersonaTariCompleta(pc);
					throw new RuntimeException("ciao");
				}catch (Exception e) {
					error(e);
					target.add(getFeedbackPanel());				
				}
				info("Informazioni aggiornate correttamente");
				target.add(EditPersonaTariPanel.this);							
				target.add(getFeedbackPanel());							
			}
						
		});
	}

}
