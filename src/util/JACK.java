package util;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;

public class JACK {

	static public String toJSON(Object o) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		//objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		return objectMapper.defaultPrettyPrintingWriter().writeValueAsString(o);
	}

	static public Object fromJSON(String str, Class c) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Object o = objectMapper.readValue(str, c);
		return o;
	}
	
	static public Object copy(Object o) throws Exception{
		return fromJSON(toJSON(o), o.getClass());
	}
	
	public static void main(String s[]) throws Exception{
		System.out.println( JACK.toJSON(new Date()) );
	}
}
