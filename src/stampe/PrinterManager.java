package stampe;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/*
 * PrinterManager : http://freemarker.org/
 */
public class PrinterManager {

	public String stampa(String nomeTemplate, Object model) {
		// Freemarker configuration object
		Configuration cfg = new Configuration();
		try {
			// Load template from source folder
			Template template = cfg.getTemplate(nomeTemplate);
			// Console output
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			template.process(model, new OutputStreamWriter(out));
			return out.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		PrinterManager pm = new PrinterManager();

		// Build the data-model
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("message", "Hello World!");

		// List parsing
		List<String> countries = new ArrayList<String>();
		countries.add("India");
		countries.add("United States");
		countries.add("Germany");
		countries.add("France");

		data.put("countries", countries);

		String ret = pm.stampa("src/stampe/test.ftl", data);

		System.out.println(ret);
	}

}
