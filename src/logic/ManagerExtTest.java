package logic;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Persona;
import model.PersonaSearch;
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
		List<PersonaCompleta>  ret=managerExt.loadPersonaCompleta(ps);
		if( ret.size()!=1) throw new RuntimeException("non va");
		
		if( !pc.equals(ret.get(0)) ) throw new RuntimeException("non va");
	}
	
	

}
