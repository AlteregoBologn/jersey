package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Esenzione;
import model.Medico;
import model.Persona;
import model.PersonaSearch;
import model.Rel_Persona_Esenzione;
import model.Rel_Persona_Medico;
import modelExt.EsenzioneDiPersona;
import modelExt.MedicoDiPersona;
import modelExt.PersonaCompleta;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:context.xml" })
public class ManagerExtTest {
	@Autowired
	private ManagerExt managerExt;
	Logger log;

	public ManagerExtTest() {
		log = Logger.getAnonymousLogger();
	}

	@Test
	public void testSalvaPersonaCompleta() {
		Persona p = new Persona();
		p.setNome("Adalberto");
		p.setCognome("Carucchio");
		p.setEmail("adalbertocarucchio@gmail.com");
		p.setPassword("aldalberto2192");
		p.setCf("ALDCRU92E47B291R");
		p.setUnid(100);
		
		PersonaCompleta pc = new PersonaCompleta();
		pc.setPersona(p);

		Medico medicoUno = new Medico();
		medicoUno.setNome("Giuseppe");
		medicoUno.setCognome("Anzisi");
		medicoUno.setCf("AOLWK83V29T628C");
		medicoUno.setEmail("giuseppeanzisi@gmail.com");
		medicoUno.setUnid(90);

		Medico medicoDue = new Medico();
		medicoDue.setNome("Marco");
		medicoDue.setCognome("Mezzagamba");
		medicoDue.setCf("ALQIWO19B28A276E");
		medicoDue.setEmail("marcomezzagamba@gmail.com");
		medicoDue.setUnid(91);

		Medico medicoTre = new Medico();
		medicoTre.setNome("Elisa");
		medicoTre.setCognome("Bianchi");
		medicoTre.setCf("BIAPSL97E12X289C");
		medicoTre.setEmail("elisabianchi@gmail.com");
		medicoTre.setUnid(92);

		MedicoDiPersona mdpUno = new MedicoDiPersona();
		mdpUno.setMedico(medicoUno);
		MedicoDiPersona mdpDue = new MedicoDiPersona();
		mdpDue.setMedico(medicoDue);
		MedicoDiPersona mdpTre = new MedicoDiPersona();
		mdpTre.setMedico(medicoTre);

		Esenzione esenzioneUno = new Esenzione();
		esenzioneUno.setUnid(5);
		esenzioneUno.setDescrizione("malattie rare");

		Esenzione esenzioneDue = new Esenzione();
		esenzioneDue.setUnid(6);
		esenzioneDue.setDescrizione("diagnosi precoce tumori");

		Esenzione esenzioneTre = new Esenzione();
		esenzioneTre.setUnid(7);
		esenzioneTre.setDescrizione("test HIV");

		EsenzioneDiPersona edpUno = new EsenzioneDiPersona();
		edpUno.setEsenzione(esenzioneUno);

		EsenzioneDiPersona edpDue = new EsenzioneDiPersona();
		edpDue.setEsenzione(esenzioneDue);

		EsenzioneDiPersona edpTre = new EsenzioneDiPersona();
		edpTre.setEsenzione(esenzioneTre);

		pc.getMedici().add(mdpUno);
		pc.getMedici().add(mdpDue);
		pc.getMedici().add(mdpTre);
		pc.getEsenzioni().add(edpUno);
		pc.getEsenzioni().add(edpDue);
		pc.getEsenzioni().add(edpTre);
		
		managerExt.savePersonaCompleta(pc);

	}

}
