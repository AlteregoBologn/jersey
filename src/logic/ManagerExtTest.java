package logic;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Medico;
import model.Persona;
import model.PersonaSearch;
import model.Rel_Persona_Medico;
import modelExt.MedicoDiPersona;
import modelExt.PersonaCompleta;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:context.xml"})
public class ManagerExtTest
{
	@Autowired
	private ManagerExt managerExt;
	Logger log;

	public ManagerExtTest()
	{
		log = Logger.getAnonymousLogger();
	}

	@Test
	public void testSalvaPersonaCompleta()
	{
		Persona p = new Persona();
		p.setNome("nome");
		p.setCognome("cognome");
		p.setEmail("a");
		p.setPassword("b");
		p.setCf("cf");
		p.setUnid(null);
		p.setOperation(p.OP_INSERT);
		
		PersonaCompleta pc=new PersonaCompleta();
		pc.setPersona(p);
		
		managerExt.savePersonaCompleta(pc);
		
		PersonaSearch ps=new PersonaSearch();
		ps.setUnid(p.getUnid());
		List<PersonaCompleta> ret = managerExt.loadPersoneCompleta(ps);
		if (ret.isEmpty())
			throw new RuntimeException("L'arrayList delle Persone Complete è vuoto");
		System.out.println("L'arraylist delle Persone Complete contiene: " + ret);
		
		if (!pc.equals(ret.get(0)))
			throw new RuntimeException("Le Persone Complete sono diverse");
		System.out.println("La persona completa " +pc+ "è uguale alla persona completa dell'arraylist " +ret);
	}
	
	@Test
	public void testscegliMedico() {
		Medico m = new Medico();
		m.setUnid(82);
		Persona persona = new Persona();
		persona.setUnid(138);
		PersonaCompleta pc = new PersonaCompleta();
		pc.setPersona(persona);
		MedicoDiPersona medicoAttivo = managerExt.getMedicoAttivo(pc);
		medicoAttivo.setMedico(m);
		pc.getMedici().add(medicoAttivo);
		Rel_Persona_Medico relazione=new Rel_Persona_Medico();
		relazione.setIdmedico(m.getUnid());
		relazione.setIdpersona(pc.getPersona().getUnid());
		medicoAttivo.setRelazione(relazione);
		managerExt.inserisciRel_Persona_Medico(relazione);
		System.out.println(medicoAttivo.toString());
		
	}
	
	@Test
	public void testSalvaMedicoDiPersona(){
		MedicoDiPersona mp = new MedicoDiPersona();
		if(mp.isInsert()){
			managerExt.inserisciRel_Persona_Medico(mp.getRelazione());
		}		
	}

	
	
	
	
}
