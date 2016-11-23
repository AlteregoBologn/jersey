package stampe;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import logic.Manager;
import model.Persona;
import model.PersonaSearch;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:context.xml" })
public class PrinterManagerTest {
	
	@Autowired
	private Manager manager;
	
	@Autowired
	private PrinterManager printerManager;
	
	Logger log;

	public PrinterManagerTest() {
		log = Logger.getAnonymousLogger();
	}

	@Test
	public void testStampaPersona() {
		File file=new File(".");
		System.out.println(file.getAbsolutePath());
		List<Persona> ret = manager.cercaPersone(new PersonaSearch());
		Map model=new HashMap();
		model.put("persone",ret);
		log.warning( printerManager.stampa("src/stampe/persona.ftl", model) );
	}

}
