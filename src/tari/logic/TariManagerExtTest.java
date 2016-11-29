package tari.logic;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tari.model.PersonaTari;
import tari.model.PersonaTariSearch;
import tari.modelExt.DichiarazioneDiImmobile;
import tari.modelExt.DichiarazioneDiPersonaTari;
import tari.modelExt.PersonaTariCompleta;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:context.xml"})
public class TariManagerExtTest {
	
	@Autowired
	private TariManagerExt tariManagerExt;
	
	@Test
	public void testSalvaPersonaTariCompleta()
	{
		PersonaTari p = new PersonaTari();
		p.setNome("nome");
		p.setCognome("cognome");
		p.setEmail("a");
		p.setPassword("b");
		p.setCf("cf");
		p.setUnid(null);
		p.setOperation(p.OP_INSERT);
		
		PersonaTariCompleta pc=new PersonaTariCompleta();
		pc.setPersonaTari(p);
		
		List<DichiarazioneDiPersonaTari> dichiarazioni = null;

		pc.setDichiarazioni(dichiarazioni);
		
		List<DichiarazioneDiImmobile> dichiarazioniDiImmobili = null;

		pc.setDichiarazioniImmobili(dichiarazioniDiImmobili);
		
		tariManagerExt.savePersonaTariCompleta(pc);
		
		PersonaTariSearch ps=new PersonaTariSearch();
		ps.setUnid(p.getUnid());
	}
}
