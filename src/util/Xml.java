/*
 * Xml.java
 *
 * Created on 21 ottobre 2006, 15.03
 */
package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.NoSuchElementException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPath;

/**
 *  Xml
 *
 *  rappresenta un file xml accessibile mediante xpath.
 *
 * @version $Revision: 1.2 $
 * @author Andrea Pradarelli
 *
 */
public class Xml {
    static SAXBuilder builder = new SAXBuilder();
   
    protected static final String NAMESPACE = "d";
    protected Document document;

    public Xml() {
        document = null;
        Element root = new Element("root");
        document = new Document(root);
        //document.setRootElement(root);
    }

    public Xml(Document document) {
        this.document = document;
    }

    public Xml(String xml) throws Exception {
        readString(xml);
    }

    public Xml(InputStream in) throws Exception {
        read(in);
    }

    public void ignoreNamespaces() {
    	for (Element el : document.getRootElement().getDescendants(new ElementFilter())) {
    	    if (el.getNamespace() != null) el.setNamespace(null);
    	    if( el.hasAttributes() ) {
    	    	for(Attribute a:el.getAttributes()){
    	    		//System.out.println(a.getName()+"="+a.getValue());
    	    		a.setNamespace(null);
    	    	}
    	    }
    	}
    }
    
    public void read(InputStream in) throws Exception {
    	builder.setIgnoringBoundaryWhitespace(false);
    	builder.setIgnoringElementContentWhitespace(false);
        document = (Document) builder.build(in);
        // System.out.println("NS:"+document.getRootElement().getNamespacesIntroduced());
    }

    private String stripXmlHeader(String xml) {
    	/*
        if (xml.startsWith("<?xml")) {
            xml = xml.substring(xml.indexOf('\n') + 1);
        }
        */
        return xml;
    }

    public void readString(String xml) throws Exception {
    	String xml2=stripXmlHeader(xml);
        read(new ByteArrayInputStream(xml2.getBytes()));
    }

    /*
     *  /
     *  [
     *  @
     */
    public void add(String path, String textValue) {
        String p[] = path.split("/");
        int i;
        Element cur = document.getRootElement();
        for (i = 1; i < p.length; i++) {
            String tagFind = p[i];
            // cerco il tagname
            String tagName = "";
            String attrs = "";
            int pos = tagFind.indexOf(" ");
            if (pos >= 0) {
                tagName = tagFind.substring(0, pos);
                attrs = tagFind.substring(pos + 1);
            } else {
                tagName = tagFind;
                attrs = "";
            }
            // indice i-esimo
            int posIdx = tagName.indexOf("[");
            String indexS = "";
            int index = 0;
            if (posIdx >= 0) {
                indexS = tagName.substring(posIdx + 1, tagName.length() - 1);
                index = Integer.parseInt(indexS);
                tagName = tagName.substring(0, posIdx);
            } else {
                index = -1;
            }
            //
            Element el = null;
            if (index < 0) {
                el = cur.getChild(tagName);
                if (el == null) {
                    el = new Element(tagName);
                    cur.addContent(el);
                }
            } else {
                List l = cur.getChildren(tagName);
                if (l == null) {
                    el = new Element(tagName);
                    cur.addContent(el);
                } else {
                    // indice maggiore
                    if (l.size() <= index) {
                        el = new Element(tagName);
                        cur.addContent(el);
                    } else {
                        el = (Element) l.get(index);
                    }
                }
            }
            // setto gli attribi sono separati da #
            String attribi[] = attrs.split("#");
            int j;
            for (j = 0; j < attribi.length; j++) {
                String attribo = attribi[j];
                String attribos[] = attribo.split("=");
                if (attribos.length > 1) {
                    String aName = attribos[0];
                    String aValue = attribos[1];
                    el.setAttribute(aName, aValue);
                }
            }
            // vado oltre
            cur = el;
        }
        // cerco il text value impostato con $
        cur.setText(textValue);
    }

    private XPath xpath(String xPathExpression) throws Exception {
        XPath xPath = XPath.newInstance(xPathExpression);
        
        String uri = document.getRootElement().getNamespaceURI();
        if (uri != null && !"".equals(uri)) {
        	for( Namespace n: document.getRootElement().getNamespacesIntroduced() ) {
            	xPath.addNamespace(n);
        	}
            //xPath.addNamespace(NAMESPACE, uri);
        }
        // xPath.
        //xPath.setNamespaceContext(resolver);
        return xPath;
    }

    public String get(String xPathExpression) throws Exception {
        XPath xPath = xpath(xPathExpression);
        Element element = (Element) xPath.selectSingleNode(document);
        if (element == null) {
            return null;
        }
        //if( element instanceof Attribute ) return element.getValue();
        return element.getText()+"/V/"+element.getValue();
    }

    public void set(String XPathExpression, String propertyValue) throws Exception {
        XPath xPath = xpath(XPathExpression);
        Element element = (Element) xPath.selectSingleNode(document);
        if (element == null) {
            throw new NoSuchElementException("can't find node for XPathExpression " + XPathExpression);
        }
        element.setText(propertyValue);
    }

    public void setElement(String XPathExpression, Element el) throws Exception {
        XPath xPath = xpath(XPathExpression);
        Element element = (Element) xPath.selectSingleNode(document);
        if (element == null) {
            throw new NoSuchElementException("can't find node for XPathExpression " + XPathExpression);
        }
        element.getChildren().add(el.clone());
    }

    public Element getElement(String XPathExpression) throws Exception {
        XPath xPath = xpath(XPathExpression);
        Element element = (Element) xPath.selectSingleNode(document);
        if (element == null) {
            throw new NoSuchElementException("can't find node for XPathExpression " + XPathExpression);
        }
        return element;
    }

    public void replaceElement(String XPathExpression, Element el) throws Exception {
        XPath xPath = xpath(XPathExpression);
        Element element = (Element) xPath.selectSingleNode(document);
        if (element == null) {
            throw new NoSuchElementException("can't find node for XPathExpression " + XPathExpression);
        }
        element.getParent().getContent().clear();
        element.getParent().getContent().add(el.clone());
    }

    public void setAll(String XPathExpression, String[] propertyValues) throws Exception {
        XPath xPath = xpath(XPathExpression);
        List list = xPath.selectNodes(document);
        for (int i = 0; i < list.size(); i++) {
            Element element = (Element) list.get(i);
            element.setText(propertyValues[i]);
        }
    }

    public String[] getAll(String XPathExpression) throws Exception {
        try {
            XPath xPath = xpath(XPathExpression);
            List list = xPath.selectNodes(document);
            String[] result = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
            	Object o=list.get(i);
            	if( o instanceof Attribute ) {
            		Attribute element = (Attribute) o;
                    result[i] = (element == null) ? null : element.getValue();
            	} else {
            		Element element = (Element) o;
                    result[i] = (element == null) ? null : element.getText();
            	}
                
            }
            return result;
        } catch (JDOMException je) {
            throw new RuntimeException("Error during XPATH Evaluation " + je.getMessage());
        }
    }
    
    public List<Element> getAllElement(String XPathExpression) throws Exception {
        try {
            XPath xPath = xpath(XPathExpression);
            List list = xPath.selectNodes(document);
            return list;
        } catch (JDOMException je) {
            throw new RuntimeException("Error during XPATH Evaluation " + je.getMessage());
        }
    }
   
    public void copyToFirst(String srcXpath, Xml to, String toXpath) throws Exception {
        XPath xPath = xpath(srcXpath);
        Element element = (Element) xPath.selectSingleNode(document);
        if (element == null) {
            throw new NoSuchElementException("can't find node for XPathExpression " + srcXpath);
        }
        Element el = to.getElement(toXpath);
        el.getChildren().add(0, element.clone());
    }

    public void copyToLast(String srcXpath, Xml to, String toXpath) throws Exception {
        XPath xPath = xpath(srcXpath);
        Element element = (Element) xPath.selectSingleNode(document);
        if (element == null) {
            throw new NoSuchElementException("can't find node for XPathExpression " + srcXpath);
        }
        Element el = to.getElement(toXpath);
        el.getChildren().add(element.clone());
    }

    public void copyToAfter(String srcXpath, Xml to, String toXpath, String name) throws Exception {
        XPath xPath = xpath(srcXpath);
        Element element = (Element) xPath.selectSingleNode(document);
        if (element == null) {
            throw new NoSuchElementException("can't find node for XPathExpression " + srcXpath);
        }
        Element dest = to.getElement(toXpath);
        if (dest == null) {
            throw new NoSuchElementException("can't find node for XPathExpression " + srcXpath);
        }
        List list = dest.getChildren();
        for (int i = 0; i < list.size(); i++) {
            Element ch = (Element) list.get(i);
            if (name.equals(ch.getName())) {
                list.add(i + 1, element.clone());
                return;
            }
        }
    }

    public void copyTo(Xml to, String toXpath) throws Exception {
        to.setElement(toXpath, document.getRootElement());
    }

    public void rename(String srcXpath, String newName) throws Exception {
        XPath xPath = xpath(srcXpath);
        List list = xPath.selectNodes(document);
        if (list == null) {
            throw new NoSuchElementException("can't find node for XPathExpression " + srcXpath);
        }
        for (int i = 0; i < list.size(); i++) {
            Element element = (Element) list.get(i);
            element.setName(newName);
        }
    }

    public int count(String srcXpath) throws Exception {
        XPath xPath = xpath(srcXpath);
        List elements = xPath.selectNodes(document);
        if (elements == null) {
            throw new NoSuchElementException("can't find node for XPathExpression " + srcXpath);
        }
        return elements.size();
    }

    public void remove(String path) throws Exception {
        XPath xPath = xpath(path);
        List list = xPath.selectNodes(document);
        for (int i = 0; i < list.size(); i++) {
            Element element = (Element) list.get(i);
            Element parent = element.getParentElement();
            parent.removeContent(element);
        }
    }

    public void write(OutputStream os) throws Exception {
        XMLOutputter xmlOutputter = new XMLOutputter();
        //xmlOutputter.setEncoding("UTF-8");
        //xmlOutputter.setIndent(true);
        //xmlOutputter.setIndentSize(1);
        //xmlOutputter.setExpandEmptyElements(false);
        //xmlOutputter.setTextNormalize(true);
        //xmlOutputter.setTrimAllWhite(true);
        //xmlOutputter.setNewlines(true);
        xmlOutputter.output(document, os);
    }

    

    public String toString() {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            write(b);
            return new String(b.toByteArray());
        } catch (Exception e) {
        }
        return "ERROR";
    }

    public Element getRoot() throws Exception {
        return document.getRootElement();
    }

    public Xml copy() {
        return new Xml();
    }

    public static void main(String s[]) throws Exception {
        Xml a = new Xml();
        a.add("/pippo", "ciao");
        a.add("/a", "azzo");
        a.add("/b/c[0]/", "b");
        a.add("/b/c[1]/", "b");
        System.out.println(" a = \n" + a);
        String aa = a.get("/root/a");
        System.out.println("a=" + aa);

        Xml x = new Xml();
        x.readString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml xmlns=\"pippo\"><pippo name=\"pluto\">ciao</pippo><pippo/><pippo/><pluto>ciao</pluto></xml>");
        System.out.println(" count= " + x.count("//d:pippo"));
        String value[] = x.getAll("//d:pippo[@name='pluto']");
        for (int i = 0; i < value.length; i++) {
            System.out.println("value=" + value[i]);
        }
        x.set("//d:pippo[@name='pluto']", "ciaoz");
        System.out.println("get=" + x.get("//d:pippo[@name='pluto']"));
        System.out.println("out=" + x);

        x.rename("//d:pippo", "pluto");
        System.out.println("rename pippo as pluto =\n" + x);

        x.remove("//d:pluto");
        System.out.println("removed pluto =\n" + x);

        a.copyToFirst("//d:b", x, "/d:xml");
        System.out.println("copy a to xml \n" + x);

        a.copyToAfter("//d:a", x, "/d:xml", "b");
        System.out.println("copy a after a \n" + x);

        a.copyToLast("//d:b", x, "/d:xml");
        System.out.println("append a after a \n" + x);

//        Xml pid3=new Xml( AFile.fileName("./in.xml") );
//        
//        Xml x2=new Xml( AFile.fileName("./in.xml") );
//        //pid3.copyTo( x2, "//hl7:book/hl7:chapter[position()=2]" );
//        //x.copyTo("//hl7:OSQ_Q06", x2, "//hl7:book/hl7:chapter[position()=1]");
//        //x.copyTo("//hl7:OSQ_Q06", x2, "//hl7:book/hl7:chapter[position()=3]");
//        //x2.set("//hl7:QRF.5[position()=1]", "ciao"); 
//        //x2.rename("//hl7:book", "book_renamed_to_pippo");
//        x2.remove("//hl7:book/hl7:chapter[position()=2]");
//        x2.write( new FileOutputStream( AFile.fileName("./test/out.xml") ) );

    }
}
