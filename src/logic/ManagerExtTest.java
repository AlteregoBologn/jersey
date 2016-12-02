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
		//TODO da completare il test
		//List<PersonaCompleta>  ret=managerExt.loadPersonaCompleta(ps);
		//if( ret.size()!=1) throw new RuntimeException("non va");
		
		//if( !pc.equals(ret.get(0)) ) throw new RuntimeException("non va");
	}
	
	@Test
	public void relazionePersonaMedico() {
		Medico m = new Medico();
		m.setUnid(79);
		PersonaCompleta pc = new PersonaCompleta();
		//pc.setPersona(persona);
		MedicoDiPersona medicoAttivo = pc.getMedicoAttivo();
		medicoAttivo.setMedico(m);
		Rel_Persona_Medico relazione=new Rel_Persona_Medico();
		relazione.setIdmedico(m.getUnid());
		relazione.setIdpersona(pc.getPersona().getUnid());
		medicoAttivo.setRelazione(relazione);
		System.out.println("Relazione:idMedico "+ relazione.getIdmedico() + " idPersona: "+ relazione.getIdpersona());
		
	}
	
	@Test
	public void salvaMedicoDiPersona(){
		Medico m = new Medico();
		m.setUnid(79);
		MedicoDiPersona medicoattivo = new MedicoDiPersona();
		medicoattivo.setMedico(m);
		Persona p = new Persona();
		p.setUnid(29);
		PersonaCompleta pc = new PersonaCompleta();
		pc.setPersona(p);
		Rel_Persona_Medico rpm = new Rel_Persona_Medico();
		rpm.setIdmedico(m.getUnid());
		rpm.setIdpersona(p.getUnid());
	}

}
