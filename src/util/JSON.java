package util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;


/**
 *  L/R Parser per JSON :
 *
 *  {} = object = treemap
 *  [] = array = arraylist
 *  string = String
 *  number = Double
 *  null = null
 *
 * @version $Revision: 1.1 $
 * @author Andrea Pradarelli
 */
public class JSON {

    byte[] buffo;
    int pos = 0;
    int last = 0;
    String BLANKS = " \t\r\n";
    String cur = "";
    String DEFAULT_ENCODING="UTF-8";
    
    JSON(String str) {
        try {
            buffo = str.getBytes(DEFAULT_ENCODING);
        } catch (Exception e) {
            buffo = str.getBytes();
        }
        pos = 0;
        last = buffo.length;
        next();
    }

    final int getch() {
        int ret;

        if (pos < last) {
            ret = buffo[pos++];
        } else {
            ret = -1;
        }

        return ret;
    }

    final void ungetch() {
        pos--;
        if (pos < 0) {
            pos = 0;
        }
    }

    final boolean isBlank(int ch) {
        switch (ch) {
            case ' ':
            case '\r':
            case '\n':
            case '\t':
                return true;
        }
        return false;

    }

    final boolean isAlpha(int ch) {
        if (ch >= 'a' && ch <= 'z') {
            return true;
        }
        if (ch >= 'A' && ch <= 'Z') {
            return true;
        }
        return false;
    }

    final boolean isString(int ch) {
        if (ch >= 33 && ch < 127) {
            return true;
        }
        return false;
    }

    final boolean isDigit(int ch) {
        if (ch >= '0' && ch <= '9') {
            return true;
        }
        if (ch == '.') {
            return true;
        }
        if (ch == '-') {
            return true;
        }
        return false;
    }

    final boolean isName(int ch) {
        if (ch == '$') {
            return true;
        }
        if (ch == '_') {
            return true;
        }
        if (isAlpha(ch)) {
            return true;
        }
        return false;
    }

    final void skipblks() {
        int ch;
        ch = getch();
        while (isBlank(ch)) {
            ch = getch();
        }
        ungetch();
        return;
    }

    final int getchS(int start) {
        int ch = getch();
        if (ch == '\\') {
            int ch2 = getch();
            switch (ch2) {
                case '\\':
                    return '\\';
                case '\'':
                    return '\'';
                case '"':
                    return '"';
                case '/':
                    return '/';
                case 'f':
                    return '\f';
                case 'b':
                    return '\b';
                case 'r':
                    return '\r';
                case 'n':
                    return '\n';
                case 't':
                    return '\t';
                case 'u': {
                    char a = (char) getch();
                    char b = (char) getch();
                    char c = (char) getch();
                    char d = (char) getch();
                    ch = Integer.parseInt("" + a + b + c + d, 16);
                    return ch;
                }
                default:
                    ungetch();
            }
        } else if (ch == start) {
            return -1;
        }
        return ch;
    }

    final String next() {
        int ch, nch, och;
        skipblks();
        ch = getch();
        if (ch == '{') {
            cur = "{";
        } else if (ch == ',') {
            cur = ",";
        } else if (ch == '}') {
            cur = "}";
        } else if (ch == '[') {
            cur = "[";
        } else if (ch == ']') {
            cur = "]";
        } else if (ch == ':') {
            cur = ":";
        } else if (ch == '"' || ch == '\'') {          // string
            och = ch;
            StringBuffer sb = new StringBuffer();
            while (true) {
                ch = getchS(och);
                if (ch < 0) {
                    break;
                }
                sb.append((char) ch);
            }
            cur = sb.toString();
        } else if (isName(ch)) {               // implicit string
            StringBuffer sb = new StringBuffer();
            while (true) {
                if (!isName(ch)) {
                    break;
                }
                sb.append((char) ch);
                ch = getch();
            }
            cur = sb.toString();
            ungetch();
        } else if (isDigit(ch)) {              // number
            StringBuffer sb = new StringBuffer();
            while (true) {
                if (!isDigit(ch)) {
                    break;
                }
                sb.append((char) ch);
                ch = getch();
            }
            cur = sb.toString();
            ungetch();
        }

        return cur;
    }

    String cur() {
        return cur;
    }

    boolean match(String what) {
        if (what.equals(cur)) {
            return true;
        }
        return false;
    }

    void error(String str) {
        String where = new String(buffo);
        where = where.substring(pos);
        throw new RuntimeException(str + "\n\n" + where);
    }

    void exactmatch(String what) {
        if (!match(what)) {
            error("Syntax Error: expected " + what);
        }
        next();
    }

    /**
     *  Produzioni.
     */
    String string() {
        //String ret = cur();
        return cur;
    }

    Object array() {
        ArrayList ret = new ArrayList();
        boolean again = true;
        if (match("[")) {
            next();
            if (!match("]")) {
                while (again) {
                    Object value = value();
                    ret.add(value);
                    if (match(",")) {
                        next();
                        again = true;
                    } else {
                        again = false;
                    }
                }
                exactmatch("]");
            }
        }
        return ret;
    }

    Object object() {
        TreeMap ret = new TreeMap();
        boolean again = true;

        if (match("{")) {
            next();
            if (match("}")) {
                next();
            } else {
                while (again) {
                    String name = string();
                    next();
                    exactmatch(":");
                    Object value = value();
                    ret.put(name, value);
                    if (match(",")) {
                        next();
                        again = true;
                    } else {
                        again = false;
                    }
                }
                exactmatch("}");
            }
        }
        return ret;
    }

    Object value() {
        Object ret = null;

        if (match("{")) {
            ret = object();
        } else if (match("[")) {
            ret = array();
        } else if (match("true")) {
            ret = "_true";
            next();
        } else if (match("false")) {
            ret = "_false";
            next();
        } else if (match("null")) {
            ret = null;
            next();
        } else {
            ret = string();
            next();
        }

        return ret;
    }

    Object parse() {
        return value();
    }

    /** da string a js string */
    static public String string2JS(String s) {
        StringBuffer ret = new StringBuffer();
        ret.append('"');
        String r = "";
        if (s == null) {
            s = "";
        }
        byte b[] = s.getBytes();
        int i;
        for (i = 0; i < b.length; i++) {
            switch (b[i]) {
                case '\n':
                    r = "\\n";
                    break;
                case '\r':
                    r = "\\r";
                    break;
                case '\b':
                    r = "\\b";
                    break;
                case '\t':
                    r = "\\t";
                    break;
                case '\\':
                    r = "\\\\";
                    break;
                case '\"':
                    r = "\\\"";
                    break;
                default: {
                    r = "" + (char) b[i];
                }
                break;
            }
            ret.append(r);
        }
        ret.append('"');
        return ret.toString();
    }

    static private String out(String pre, Object o) {
        StringBuffer sb = new StringBuffer();

        pre += " ";
        if (o == null) {
            return "null";
        } else if (o instanceof List) {
            sb.append("[\n");
            List al = (List) o;
            Iterator i = al.iterator();
            while (i.hasNext()) {
                Object oo = i.next();
                sb.append(out(pre, oo));
                if (i.hasNext()) {
                    sb.append(",\n");
                }
            }
            sb.append("]\n");
        } else if (o instanceof TreeMap) {
            sb.append("{\n");
            TreeMap al = (TreeMap) o;
            Iterator i = al.keySet().iterator();
            while (i.hasNext()) {
                String name = (String) i.next();
                Object oo = al.get(name);
                sb.append(string2JS(name));
                sb.append(':');
                sb.append(out(pre, oo));
                if (i.hasNext()) {
                    sb.append(",\n");
                }
            }
            sb.append("}\n");
            //} else if( o instanceof String ) {
            //    String ret=string2JS( (String)o );
            //    sb.append( ret+"\n" );
        } else {
            String ret = beanToJSON(pre, o);
            sb.append(ret + "\n");
        }
        return sb.toString();
    }

    static public String toJSONString(Object o) {
        return out("", o);
    }

    static private String canonicalize(String in) {
        if (in != null) {
            in = in.replaceAll("/", "_");

            //via i $ per ws6
            in = in.replaceAll("\\$", "_");
            in = in.toUpperCase();
        }
        return in;
    }

    static private String outx(String pre, String nome, Object o) {
        StringBuffer sb = new StringBuffer();
        String opre = pre;
        String cat = "";
        String cr = "\n";
        Iterator i;
        TreeMap tm;
        ArrayList al;

        nome = canonicalize(nome);
        if (o == null) {
            return "";
        } else if (o instanceof ArrayList) {
            al = (ArrayList) o;
            i = al.iterator();
            pre += cat;
            int n = 0;
            while (i.hasNext()) {
                Object oo = i.next();
                if (nome != null) {
                    sb.append("<" + nome + ">");
                }
                sb.append(outx(pre, null, oo));
                if (nome != null) {
                    sb.append("</" + nome + ">\n");
                }
                n++;
            }
            pre = opre;
        } else if (o instanceof TreeMap) {
            tm = (TreeMap) o;
            i = tm.keySet().iterator();
            pre += cat;
            if (nome != null) {
                sb.append("<" + nome + ">");
            }
            while (i.hasNext()) {
                String name = (String) i.next();
                Object oo = tm.get(name);
                sb.append(outx(pre, name, oo));
            }
            if (nome != null) {
                sb.append("</" + nome + ">\n");
            }
            //pre = opre;
        } else { // if( o instanceof String ) {
            String ret = (String) o;
            if (nome != null) {
                sb.append("<" + nome + ">");
            }
            if (ret != null && ret.length() >= 0) {
                sb.append("<![CDATA[" + ret + "]]>");
            } else {
                sb.append("");
            }
            if (nome != null) {
                sb.append("</" + nome + ">\n");
            }
//        } else {
//            String str=JSON.beanToJSONString(o);
//            Object v=JSON.parseJSON(str);
//            sb.append( outx(pre,nome,v) );
        }
        return sb.toString();
    }

    static public String toXML(String root, Object o) throws Exception {
        Object value = o;
        String valueS = "";

        String str = JSON.beanToJSONString(o);
        value = parseJSON(str);

        root = canonicalize(root);
        String xml = "<" + root + ">"; // "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
        xml += outx("", null, value);
        xml += "</" + root + ">";
        return xml;
    }

    static private String beanToJSON(String pre, Object o) {
        try {
            StringBuffer sb = new StringBuffer();
            if (pre.length() > 5) {
                return "";
            }

            if (o == null) {
                sb.append("null");
            } else if (o instanceof Class) {
                sb.append(string2JS("" + o));
                //} else if( o instanceof Throwable ) {
                //  sb.append( Utils.t2s( (Throwable)o ) );
            } else if (o instanceof Date) {
                SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddHHmmss");
                sb.append( string2JS(sf.format((Date)o) ) );
            } else if (o instanceof String) {
                sb.append(string2JS((String) o));
            } else if (o instanceof Boolean) {
                sb.append(o);
            } else if (o instanceof Number) {
                sb.append(string2JS("" + (Number) o));
            } else if (o instanceof List) {
                sb.append(JSON.toJSONString(o));
            } else if (o instanceof Map) {
                sb.append(JSON.toJSONString(o));
            } else {
                Class c = o.getClass();
                sb.append(pre + "{\n");
                String pre2 = pre + "\t";
                BeanInfo info = Introspector.getBeanInfo(c);
                PropertyDescriptor[] props = info.getPropertyDescriptors();
                for (int i = 0; i < props.length; i++) {
                    String name = props[i].getName();
                    Method m = props[i].getReadMethod();
                    Object value = m.invoke(o, new Object[]{});
                    String valueS = "";

                    sb.append(pre2);
                    sb.append("\"" + name + "\" : ");
                    sb.append(beanToJSON(pre2, value));

                    if ((i + 1) < props.length) {
                        sb.append(",");
                    }
                    sb.append("\n");
                }
                sb.append(pre + "}\n");
            }
            return sb.toString();
        } catch (IntrospectionException ex) {
            return "{ \"error\" : 'CANNOT JSONIZE " + o + "'}\n";
        } catch (IllegalAccessException ex) {
        	return "{ \"error\" : 'CANNOT JSONIZE " + o + "'}\n";
        } catch (InvocationTargetException ex) {
        	return "{ \"error\" : 'CANNOT JSONIZE " + o + "'}\n";
        }
    }

    static public String beanToJSONString(Object o) {
        return beanToJSON("", o);
    }

    static public Object parseJSON(String str) {
        if (str == null) {
            str = "";
        }
        JSON p = new JSON(str);
        return p.parse();
    }

    /**
     *  o = oggetto parsato da JSON ovvero
     *      ARRAY
     *      STRING
     *      MAP
     *  Path = /0/nome
     */
    public static Object get(Object o, String path) {
        String key[] = path.split("/");
        String ret = null;
        int n = 1;
        while (n < key.length) {
            if (o instanceof TreeMap) {
                TreeMap m = (TreeMap) o;
                o = m.get(key[n++]);
            } else if (o instanceof ArrayList) {
                ArrayList al = (ArrayList) o;
                int pos = Integer.parseInt(key[n++]);
                o = al.get(pos);
            } else if (o instanceof String) {
                break;
            } else {
                break;
            }
        }
        return o;
    }
    
    public static String getString(Object o,String path) {
    	return (String) get(o,path);
    }
    
    public static float getFloat(Object o,String path) {
    	return Float.parseFloat(getString(o,path));
    }
    
    public static int getInt(Object o,String path) {
    	return (int)getFloat(o,path);
    }
    

    
    public static Date getDate(Object o,String path) {
    	SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddHHmmss");
    	try {
			return sf.parse(getString(o,path));
		} catch (ParseException e) {			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
    
    public static Object eval(String str) {
        return parseJSON(str);
    }

    // indenta il JSON passato
    public String indent(String in) {
        StringBuffer ret = new StringBuffer();
        StringTokenizer sr = new StringTokenizer(in, "\n");
        while (sr.hasMoreTokens()) {
            String line = sr.nextToken();
            ret.append(line);
        }
        return ret.toString();
    }

    // beanshella il JSON passato
    static public String beanToBsh(String path, Object o) {
        try {
            StringBuffer sb = new StringBuffer();

            if (o == null) {
                // sb.append(path+"=");
                // sb.append( "null ;\n" );
            } else if (o instanceof Class) {
                //} else if( o instanceof Throwable ) {
                //  sb.append( Utils.t2s( (Throwable)o ) );
            } else if (o instanceof String) {
                sb.append(path + "=");
                sb.append(string2JS((String) o));
                sb.append(";\n");
            } else if (o instanceof Boolean) {
                sb.append(path + "=");
                sb.append(o);
                sb.append(";\n");
            } else if (o instanceof Number) {
                sb.append(path + "=");
                sb.append(o);
                sb.append(";\n");
            } else if (o instanceof List) {
                List l = (List) o;
                int cnt = 0;
                for (Object oo : l) {
                    sb.append(beanToBsh(path + ".get(" + cnt + ")", oo));
                    cnt++;
                }
            } else if (o instanceof Map) {
                Map m = (Map) o;
                for (Object oo : m.keySet()) {
                    String name = (String) oo;
                    if( "class".equals(name) ) continue;
                    Object value = m.get(oo);
                    sb.append(beanToBsh(path + "." + name, value));
                }
            } else {
                Class c = o.getClass();
                BeanInfo info = Introspector.getBeanInfo(c);
                PropertyDescriptor[] props = info.getPropertyDescriptors();
                for (int i = 0; i < props.length; i++) {
                    String name = props[i].getName();
                    if( "class".equals(name) ) continue ;

                    Method m = props[i].getReadMethod();
                    Object value = m.invoke(o, new Object[]{});

                    sb.append(beanToBsh(path + "." + name, value));
                }
            }
            return sb.toString();
        } catch (IntrospectionException ex) {
            return "{ \"error\" : 'CANNOT bsh " + o + "'}\n";
        } catch (InvocationTargetException ex) {
            return "{ \"error\" : 'CANNOT bsh " + o + "'}\n";
        } catch (IllegalAccessException ex) {
        	return "{ \"error\" : 'CANNOT bsh " + o + "'}\n";
        }
    }


    public static void main(String s[]) throws Exception {
        //String str="[{a:'zx\"1',b:[1,'ziguli',{pippo:17},2,3,4,5],c:57},{a:'zx2'}]";
        String str = "{\"LOC_AUSL\":{\"azn_cod\":\"105\",\"desc\":\"AZIENDA USL DI BOLOGNA\"},\"LOC_DISTRETTO\":{\"di_cod\":\"\",\"desc\":\"\"},\"LOC_NCP\":" +
                "{\"pk\":\" \",\"desc\":\" \"},\"MED_INS_TIPO_MEDICO\":{\"tipo_medico\":\"1\",\"desc\":\"MMG\"},\"MED_INS_CODICE_FISCALE\":\"prdndr70t11a944a\"," +
                "\"MED_INS_MATRICOLA\":\" \",\"MED_INS_CODICE_GENERALE\":\" \",\"MED_INS_ASSONANZA\":\"PRATARELIANTR\",\"MED_INS_COGNOME\":\"PRADARELLI\"," +
                "\"MED_INS_NOME\":\"ANDR\",\"MED_INS_SESSO\":{\"pk\":\"M\",\"desc\":\"M\"},\"MED_INS_DATA_NASCITA\":\"11/12/2970\"," +
                "\"MED_INS_COMUNE_NASCITA\":{\"cod_rg\":\"080\",\"cod_com\":\"037006\",\"desc\":\"BOLOGNA\",\"catasto\":\"A944\",\"cap\":\"40100\"," +
                "\"provincia\":\"BO\"},\"MED_INS_COMUNE_RESIDENZA\":{},\"MED_INS_PROVINCIA_RESIDENZA\":\"\",\"MED_INS_INDIRIZZO_RESIDENZA\":\"\",\"MED_INS_CAP_RESIDENZA\":\"\"," +
                "\"MED_INS_TELEFONO_RESIDENZA\":\"\",\"MED_INS_CELLULARE_RESIDENZA\":\"\",\"MED_INS_FAX_RESIDENZA\":\"\",\"MED_INS_EMAIL_RESIDENZA\":\"\",\"MED_INS_COMUNE_RECAPITO\":{}," +
                "\"MED_INS_PROVINCIA_RECAPITO\":\"\",\"MED_INS_INDIRIZZO_RECAPITO\":\"\",\"MED_INS_CAP_RECAPITO\":\"\",\"MED_INS_TELEFONO_RECAPITO\":\"\",\"MED_INS_CELLULARE_RECAPITO\":\"\"" +
                ",\"MED_INS_FAX_RECAPITO\":\"\",\"MED_INS_EMAIL_RECAPITO\":\"\"}";

        // str="{\"MED_INS_COMUNE_RESIDENZA\":{},\"MED_INS_PROVINCIA_RESIDENZA\":\"ciao\"}";
/*
        byte buffo[]=new byte[10000];
        FileInputStream fin=new FileInputStream("c:/tmp/json.txt");
        fin.read(buffo);
        fin.close();
        str=new String( buffo );
         */

        Object ret = JSON.parseJSON(str);

        // System.out.println("/AZIENDA_AUSL_V="+JSON.get( ret, "/AZIENDA_AUSL_V" ));

        //System.out.println("RET ="+JSON.toJSONString(ret));
        //System.out.println("XML =\n"+JSON.toXML("root",ret));
        System.out.println("bsh=\n" + JSON.beanToBsh("oggio", ret));
        //System.out.println("RET=\r\n"+JSON.toXML(ret));

        System.out.println(" json "+ JSON.beanToJSON( "", new Date() ) );
    }
}

