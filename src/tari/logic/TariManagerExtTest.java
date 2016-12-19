package tari.logic;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Indirizzo;
import tari.dao.relationDao.Rel_Persona_DichiarazioneDao;
import tari.model.Allegato;
import tari.model.Dichiarazione;
import tari.model.Immobile;
import tari.model.Locale;
import tari.model.PersonaGiuridica;
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

//	@Test
//	public void testSalvaPersonaTariCompleta() throws Exception {
//		
//		PersonaTari p = new PersonaTari();
//		p.setUnid(12);
//		p.setNome("Paperino");
//		p.setCognome("Paperone");
//		p.setCf("0258741369");
//		p.setCanc("N");
//		p.setPassword("p");
//		p.setEmail("gr.pilot@grf.com");
//		p.setSesso("M");
//		p.setDatanascita(new Date());
//		p.setComunenascita("Milano");
//		p.setPec("paperino@pec.it");
//		p.setRecapitoTelefonico("036974125");
//		p.setDittaIndividuale(0);
//		p.setOperation(p.OP_INSERT);
//		
//		PersonaGiuridica pg = new PersonaGiuridica();
//		pg.setUnid(null);
//		pg.setDescrizione("DittaSRL");
//		pg.setSedeLegale("Beragmo");
//		pg.setProvincia("BG");
//		pg.setIndirizzo("via brutta");
//		pg.setNumeroCivico(23);
//		pg.setpIva("12546546");
//		pg.setRecapitoTelefonico("465987621");
//		pg.setEmail("alterego@alterego.it");
//		pg.setPec("ditta@pec.it");
//		pg.setOperation(pg.OP_INSERT);
//		
//		Indirizzo indirizzo = new Indirizzo();
//		indirizzo.setCap("123654");
//		indirizzo.setVia("Via Teulada");
//		indirizzo.setCivico("48");
//		indirizzo.setComune("Bologna");
//		indirizzo.setCap("549");
//		indirizzo.setOperation(indirizzo.OP_INSERT);
//		
//		PersonaTariCompleta pc=new PersonaTariCompleta();
//		pc.setOperation(pc.OP_INSERT);
//		pc.setPersonaTari(p);
//		pc.setPersonaGiuridica(pg);
//		pc.setResidenza(indirizzo);
//		
//		Allegato cartaIdentita = new Allegato();
//		cartaIdentita.setOperation(cartaIdentita.OP_INSERT);
//		cartaIdentita.setTipo("CI");
//		pc.setCartaIdentita(cartaIdentita);
//		
//		Allegato visuraCamerale = new Allegato();
//		visuraCamerale.setOperation(visuraCamerale.OP_INSERT);
//		visuraCamerale.setTipo("VC");
//		pc.setVisuraCamerale(visuraCamerale);
//			
//		Dichiarazione dichiarazione = new Dichiarazione();
//		dichiarazione.setUnid(8);
//		dichiarazione.setData(new Date());
//		dichiarazione.setFirma("Firma di prova");
//		dichiarazione.setItalianoallestero(1);
//		dichiarazione.setUnicooccupante(1);
//		dichiarazione.setAgricoltore(1);
//		
//		PrecedenteDichiarazione pd = new PrecedenteDichiarazione();
//		pd.setUnid(6);
//		pd.setCivico("2/A");
//		pd.setDataDa(new Date());
//		pd.setInterno("8");
//		pd.setMotivo("adesso non ce l'ho");
//		pd.setVia("Via di qua");
//		pd.setComunicazione("non ho comunicazioni");
//		
//		Immobile immobile = new Immobile();
//		immobile.setUnid(7);
//		immobile.setCivico("1");
//		immobile.setDatada(new Date());
//		immobile.setInterno("78/B");
//		immobile.setNomeprecedentedetentore("Ciccio Paperone");
//		immobile.setNomeproprietario("Nonna Papera");
//		immobile.setPiano("8");
//		immobile.setQualita(8);
//		immobile.setVia("Via di Mezzo");
//		
//		Locale locale = new Locale();
//		locale.setUnid(9);
//		locale.setFoglio("H6quaire");
//		locale.setMq(120);
//		locale.setParticella("Particella 9");
//		locale.setSubalterno("Alberto Brambilla");
//		locale.setTipo(2);
//		
//		LocaleDiImmobile ldi = new LocaleDiImmobile();
//		ldi.setLocale(locale);	
//
//		ImmobileDiDichiarazione idd = new ImmobileDiDichiarazione();
//		idd.setImmobile(immobile);
//		idd.getLocaliDiImmobile().add(ldi);
//
//		DichiarazioneDiPersonaTari ddpt=new DichiarazioneDiPersonaTari();
//		ddpt.setOperation(ddpt.OP_INSERT);
//		ddpt.setDichiarazione(dichiarazione);
//		ddpt.setPrecedenteDichiarazione(pd);
//		ddpt.setDichiarazioneImmobile(idd);
//		pc.getDichiarazioniDiPersona().add(ddpt);
//			
//		System.out.println(JACK.toJSON(pc));
//
//		tariManagerExt.savePersonaTariCompleta(pc);
//		
//	}
	
	@Test
	public void testSalvaUpdatePersonaTariCompleta() throws Exception {
		
//		PersonaTariSearch pts=new PersonaTariSearch();
//		pts.setUnid(908);
		
//		PersonaTariCompleta pcFound = tariManagerExt.loadPersoneTariCompleta(pts).get(0);
		
		PersonaTariCompleta pcFound = tariManagerExt.loadPersonaTariCompleta(908);
		
		// AGGIORNAMENTO DELLA PERSONA TARI.
		
		pcFound.getPersonaTari().setCognome("prova2");
		pcFound.getPersonaTari().setEmail("prova@prova2.it");
		pcFound.getPersonaTari().setComunenascita("Chieti2");
		pcFound.getPersonaTari().setDittaIndividuale(1);  // è una ditta ---> persona giuridica
		pcFound.getPersonaTari().setOperation(pcFound.OP_UPDATE);
		
		// AGGIORNAMENTO DELLA PERSONA GIURIDICA
		
		pcFound.getPersonaGiuridica().setDescrizione("ENEL IMPIANTI");
		pcFound.getPersonaGiuridica().setpIva("457895468");
		pcFound.getPersonaGiuridica().setEmail("enel@enel.it");
		pcFound.getPersonaGiuridica().setNumeroCivico(89);
		pcFound.getPersonaGiuridica().setOperation(pcFound.OP_UPDATE);
		
		// AGGIORNAMENTO INDIRIZZO DELLA PERSONA TARI.
		
		pcFound.getResidenza().setVia("Via Cattani");
		pcFound.getResidenza().setCap("41012");
		pcFound.getResidenza().setCivico("96");
		pcFound.getResidenza().setComune("Carpi");
		pcFound.getResidenza().setOperation(pcFound.OP_UPDATE);
		
		
		// AGGIORNAMENTO ALLEGATO PERSONA TARI (CARTA DI IDENTITA')      TODO test da verificare
		
//		pcFound.getCartaIdentita().setData();
//		pcFound.getCartaIdentita().setOperation(pcFound.OP_UPDATE);
		
		// AGGIORNAMENTO ALLEGATO PERSONA GIURIDICA (VISURA CAMERALE)    TODO test da verificare
		
//		pcFound.getVisuraCamerale().setData(); 
//		pcFound.getVisuraCamerale().setOperation(pcFound.OP_UPDATE);
		
		for(DichiarazioneDiPersonaTari ddpt : pcFound.getDichiarazioniDiPersona()){
			
			
			ddpt.getDichiarazione().setFirma("Firma aggiornata");
			ddpt.getDichiarazione().setItalianoallestero(0);
			ddpt.getDichiarazione().setAgricoltore(null);
			ddpt.getDichiarazione().setUnicooccupante(null);
			ddpt.getDichiarazione().setOperation(ddpt.OP_UPDATE);
			
			
			ddpt.getPrecedenteDichiarazione().setCivico("3");
			ddpt.getPrecedenteDichiarazione().setMotivo("non è stata effettuata nessuna verifica");
			ddpt.getPrecedenteDichiarazione().setVia("Via Molisana");
			ddpt.getPrecedenteDichiarazione().setOperation(ddpt.OP_UPDATE);
			
			
			ddpt.getDichiarazioneImmobile().getImmobile().setCivico("45");
			ddpt.getDichiarazioneImmobile().getImmobile().setInterno("4");
			ddpt.getDichiarazioneImmobile().getImmobile().setQualita(7);
			ddpt.getDichiarazioneImmobile().getImmobile().setOperation(ddpt.OP_UPDATE);
			
			
			
//			LocaleDiImmobile last=new LocaleDiImmobile();
//			for(LocaleDiImmobile l:p.getDichiarazioneImmobile().getLocaliDiImmobile()){
//				l.getLocale().setOperation(l.OP_UPDATE);
//				l.getLocale().setMq(56);
//				
//				last=(LocaleDiImmobile) JACK.copy(l);
//			}
//			
//			last.setOperation(p.OP_INSERT);
//			p.getDichiarazioneImmobile().getLocaliDiImmobile().add(last);
		}
		
		tariManagerExt.savePersonaTariCompleta(pcFound);
	}
}
