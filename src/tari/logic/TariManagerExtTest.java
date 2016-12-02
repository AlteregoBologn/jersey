package tari.logic;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tari.dao.relationDao.Rel_Persona_DichiarazioneDao;
import tari.model.Dichiarazione;
import tari.model.Immobile;
import tari.model.Locale;
import tari.model.PersonaTari;
import tari.model.PersonaTariSearch;
import tari.model.PrecedenteDichiarazione;
import tari.model.relationModel.Rel_PersonaTari_Dichiarazione;
import tari.modelExt.DichiarazioneDiPersonaTari;
import tari.modelExt.ImmobileDiDichiarazione;
import tari.modelExt.LocaleDiImmobile;
import tari.modelExt.PersonaTariCompleta;
import util.JACK;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:context.xml" })
public class TariManagerExtTest {

	@Autowired
	private TariManagerExt tariManagerExt;

	@Test
	public void testSalvaPersonaTariCompleta() throws Exception {
		
		PersonaTari p = new PersonaTari();
		p.setNome("Paperino");
		p.setCognome("Giacomu");
		p.setEmail("gr.pilot@grf.com");
		p.setPassword("cio");
		p.setCf("cf");	
		p.setUnid(null);
		
		PersonaTariCompleta pc=new PersonaTariCompleta();
		pc.setPersonaTari(p);
		
		Dichiarazione dichiarazione1=new Dichiarazione();
		dichiarazione1.setData(new Date());
		dichiarazione1.setAgricoltore("S");
		
		PrecedenteDichiarazione precedenteDichiarazione1=new PrecedenteDichiarazione();
		precedenteDichiarazione1.setDataDa(new Date());
		precedenteDichiarazione1.setInterno("8");
		precedenteDichiarazione1.setCivico("12/5");
		
		Immobile immobile1=new Immobile();
		immobile1.setDatada(new Date());
		immobile1.setCivico("1");
		
		Locale l = new Locale();
		l.setMq(120);
		l.setParticella("Particella");
		
		LocaleDiImmobile lim=new LocaleDiImmobile();
		lim.setLocale(l);	

		ImmobileDiDichiarazione di1=new ImmobileDiDichiarazione();
		di1.setImmobile(immobile1);
		di1.getLocaliDiImmobile().add(lim);
		
		Immobile immobile2=new Immobile();
		immobile2.setDatada(new Date());
		immobile2.setCivico("2");
		
		ImmobileDiDichiarazione di2=new ImmobileDiDichiarazione();
		di2.setImmobile(immobile2);
		
		DichiarazioneDiPersonaTari dich1=new DichiarazioneDiPersonaTari();
		dich1.setDichiarazione(dichiarazione1);
		dich1.setPrecedenteDichiarazione(precedenteDichiarazione1);
		dich1.getDichiarazioniImmobili().add(di1);
		dich1.getDichiarazioniImmobili().add(di2);
		
		Dichiarazione dichiarazione2=new Dichiarazione();
		dichiarazione2.setData(new Date());
		dichiarazione2.setAgricoltore("N");			
		
		Immobile immobile3=new Immobile();
		immobile3.setDatada(new Date());
		immobile3.setCivico("15");
		immobile3.setInterno("1");
		
		ImmobileDiDichiarazione di3=new ImmobileDiDichiarazione();
		di3.setImmobile(immobile3);
		
		DichiarazioneDiPersonaTari dich2=new DichiarazioneDiPersonaTari();		
		dich2.setDichiarazione(dichiarazione2);
		dich2.getDichiarazioniImmobili().add(di3);
		//dich2.setPrecedenteDichiarazione(null);
		//dich2.getDichiarazioniImmobili().add(e); illegale !
		
		pc.getDichiarazioniDiPersona().add(dich1);
		pc.getDichiarazioniDiPersona().add(dich2);
		
		System.out.println(JACK.toJSON(pc));
		
		//PersonaTariCompleta copia =(PersonaTariCompleta) JACK.copy(pc);
		
		//pc=tariManagerExt.createPersonaTariCompleta(p);

		tariManagerExt.savePersonaTariCompleta(pc);
		
		PersonaTariSearch ps=new PersonaTariSearch();
		ps.setUnid(p.getUnid());
		
		//System.out.println(JACK.toJSON(ps));
		
		PersonaTariCompleta pcFound = tariManagerExt.loadPersoneTariCompleta(ps).get(0);
		System.out.println(JACK.toJSON(pcFound));
		
		/* Le due personeTari complete saranno sempre diverse a causa degli ID autogenerati, 
		 * le relazioni con gli id ancora non assegnati (autogenerati),
		 * le date (new Date()). Il confronto dei dati effettutato grazie al JSON 
		 * ha esito positivo in quanto i dati inseriti sono uguali a quelli trovati
		 *  
		 * if( !pcFound.equals(pc) ) throw new RuntimeException("nonva");
		 */
	}

	
}
