package tari.logic;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tari.model.Dichiarazione;
import tari.model.DichiarazioneSearch;
import tari.model.Immobile;
import tari.model.ImmobileSearch;
import tari.model.Locale;
import tari.model.LocaleSearch;
import tari.model.PersonaGiuridica;
import tari.model.PersonaGiuridicaSearch;
import tari.model.PrecedenteDichiarazione;
import tari.model.PrecedenteDichiarazioneSearch;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:context.xml"})
public class TariManagerTest {

	@Autowired
	private TariManager tariManager;


	@Test
	public void testinserisciImmobile() {
		Immobile a = new Immobile();
		a.setCivico("20");
		a.setDatada("15-MAG-2016");
		a.setInterno("5");
		a.setNomeprecedentedetentore("Pippo Pappo");
		a.setNomeproprietario("Pluto Pap");
		a.setPiano("3");
		a.setQualita("locatario");
		a.setUnid(1);
		a.setVia("Via Ranzani");
		tariManager.inserisciImmobile(a);
	}

	@Test
	public void testcercaImmobile() {
		ImmobileSearch as = new ImmobileSearch();
		as.setUnid(1);
		tariManager.cercaImmobile(as);
	}

	@Test
	public void testdeleteImmobile() {
		Immobile a = new Immobile();
		a.setUnid(1);
		tariManager.deleteImmobile(a);
	}

	@Test
	public void testinserisciLocale() {
		Locale a = new Locale();
		a.setFoglio("50");
		a.setMq(40);
		a.setParticella("3");
		a.setSubalterno("15");
		a.setTipo("Superficie a filo dei muri dell'abitazione compresa mansarda, taverna e scale (esclusi balconi e terrazze scoperte)");
		a.setUnid(1);
		tariManager.inserisciLocale(a);
	}

	@Test
	public void testcercaLocale() {
		LocaleSearch as = new LocaleSearch();
		as.setUnid(1);
		tariManager.cercaLocale(as);
	}

	@Test
	public void testdeleteLocale() {
		Locale a = new Locale();
		a.setUnid(1);
		tariManager.deleteLocale(a);
	}

	@Test
	public void testinserisciDichiarazione() {
		Dichiarazione a = new Dichiarazione();
		a.setAgricoltore("false");
		a.setData("20-DIC-2016");
		a.setFirma("pippononsaFirmare");
		a.setItalianoallestero("false");
		a.setUnicooccupante("true");
		a.setUnid(2);
		tariManager.inserisciDichiarazione(a);
	}

	@Test
	public void testcercaDichiarazione() {
		DichiarazioneSearch as = new DichiarazioneSearch();
		as.setUnid(2);
		tariManager.cercaDichiarazione(as);
	}

	@Test
	public void testdeleteDichiarazione() {
		Dichiarazione a = new Dichiarazione();
		a.setUnid(2);
		tariManager.deleteDichiarazione(a);
	}

	@Test
	public void testinserisciPrecedenteDichiarazione() {
		PrecedenteDichiarazione a = new PrecedenteDichiarazione();
		a.setCivico("20");
		a.setDatada("15-DIC-2016");
		a.setInterno("8");
		a.setMotivo("Affitto alto");
		a.setUnid(1);
		a.setVia("Via Papa");
		tariManager.inserisciPrecedenteDichiarazione(a);
	}

	@Test
	public void testcercaPrecedenteDichiarazione() {
		PrecedenteDichiarazioneSearch as = new PrecedenteDichiarazioneSearch();
		as.setUnid(1);
		tariManager.cercaPrecedenteDichiarazione(as);
	}

	@Test
	public void testdeletePrecedenteDichiarazione() {
		PrecedenteDichiarazione a = new PrecedenteDichiarazione();
		a.setUnid(1);
		tariManager.deletePrecedenteDichiarazione(a);
	}

	@Test
	public void testinserisciPersonaGiuridica() {
		PersonaGiuridica a = new PersonaGiuridica();
		a.setDescrizione("Pippo S.R.L");
		a.setPec("pipposrl@pec.it");
		a.setPiva("frsv2954weg");
		a.setRappresentante(29);
		a.setUnid(1);		
		tariManager.inserisciPersonaGiuridica(a);
	}

	@Test
	public void testcercaPersonaGiuridica() {
		PersonaGiuridicaSearch as = new PersonaGiuridicaSearch();
		as.setUnid(1);
		tariManager.cercaPersonaGiuridica(as);
	}

	@Test
	public void testdeletePersonaGiuridica() {
		PersonaGiuridica a = new PersonaGiuridica();
		a.setUnid(1);
		tariManager.deletePersonaGiuridica(a);
	}

}
