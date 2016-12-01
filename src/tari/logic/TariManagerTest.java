package tari.logic;

import java.util.Date;

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
import tari.model.PersonaTari;
import tari.model.PersonaTariSearch;
import tari.model.PrecedenteDichiarazione;
import tari.model.PrecedenteDichiarazioneSearch;
import tari.model.relationModel.Rel_Dichiarazione_Immobile;
import tari.model.relationModel.Rel_Dichiarazione_ImmobileSearch;
import tari.model.relationModel.Rel_Dichiarazione_PrecDichiara;
import tari.model.relationModel.Rel_Dichiarazione_PrecDichiaraSearch;
import tari.model.relationModel.Rel_Immobile_Locale;
import tari.model.relationModel.Rel_Immobile_LocaleSearch;
import tari.model.relationModel.Rel_PersGiuridica_Dichiarazion;
import tari.model.relationModel.Rel_PersGiuridica_DichiarazionSearch;
import tari.model.relationModel.Rel_PersonaTari_Dichiarazione;
import tari.model.relationModel.Rel_PersonaTari_DichiarazioneSearch;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:context.xml"})
public class TariManagerTest {

	@Autowired
	private TariManager tariManager;


	@Test
	public void testinserisciPersonaTari()
	{
		PersonaTari p = new PersonaTari();
		p.setNome("nome");
		p.setCognome("cognome");
		p.setEmail("a");
		p.setPassword("b");
		p.setCf("cf");
		p.setUnid(1);
		p.setPec("blabla@pec.it");
		tariManager.inserisciPersonaTari(p);
	}

	@Test
	public void testcercaPersonaTari() {
		PersonaTariSearch as = new PersonaTariSearch();
		as.setUnid(29);
		tariManager.cercaPersonaTari(as);
	}

	@Test
	public void testupdatePersonaTari() {
		PersonaTari a = new PersonaTari();
		a.setUnid(29);
		a.setCognome("Poppo");
		tariManager.updatePersonaTari(a);
	}

	@Test
	public void testdeletePersonaTari() {
		PersonaTari a = new PersonaTari();
		a.setUnid(160);
		tariManager.deletePersonaTari(a);
	}

	@Test
	public void testinserisciImmobile() {
		Immobile a = new Immobile();
		a.setCivico("20");
		a.setDatada(new Date());
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
		a.setAgricoltore("true");
		a.setData(new Date());
		a.setFirma("agricoltoreGo");
		a.setItalianoallestero("false");
		a.setUnicooccupante("false");
		a.setUnid(4);
		tariManager.inserisciDichiarazione(a);
	}

	@Test
	public void testcercaDichiarazione() {
		DichiarazioneSearch as = new DichiarazioneSearch();
		as.setUnid(2);
		tariManager.cercaDichiarazione(as);
	}

	@Test
	public void testupdateDichiarazione() {
		Dichiarazione a = new Dichiarazione();
		a.setUnid(2);
		a.setAgricoltore("true");
		tariManager.updateDichiarazione(a);
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
		a.setDataDa(new Date());
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

	@Test
	public void testinseriscirel_Dichiarazione_Immobile() {
		Rel_Dichiarazione_Immobile a = new Rel_Dichiarazione_Immobile();
		a.setIddichiarazione(1);
		a.setIdimmobile(1);
		tariManager.inseriscirel_Dichiarazione_Immobile(a);
	}

	@Test
	public void testcercarel_Dichiarazione_Immobile() {
		Rel_Dichiarazione_ImmobileSearch as = new Rel_Dichiarazione_ImmobileSearch();
		as.setIddichiarazione(1);
		as.setIdimmobile(1);
		tariManager.cercarel_Dichiarazione_Immobile(as);
	}

	@Test
	public void testdeleterel_Dichiarazione_Immobile() {
		Rel_Dichiarazione_Immobile a = new Rel_Dichiarazione_Immobile();
		a.setIddichiarazione(1);
		a.setIdimmobile(1);
		tariManager.deleterel_Dichiarazione_Immobile(a);
	}

	@Test
	public void testinserisciRel_Persona_Dichiarazione() {
		Rel_PersonaTari_Dichiarazione a = new Rel_PersonaTari_Dichiarazione();
		a.setIddichiarazione(1);
		a.setIdpersona(29);
		tariManager.inserisciRel_Persona_Dichiarazione(a);
	}

	@Test
	public void testcercaRel_Persona_Dichiarazione() {
		Rel_PersonaTari_DichiarazioneSearch as = new Rel_PersonaTari_DichiarazioneSearch();
		as.setIdpersona(29);
		tariManager.cercaRel_Persona_Dichiarazione(as);
	}

	@Test
	public void testdeleteRel_Persona_Dichiarazione() {
		Rel_PersonaTari_Dichiarazione a = new Rel_PersonaTari_Dichiarazione();
		a.setIdpersona(29);
		tariManager.deleteRel_Persona_Dichiarazione(a);
	}

	@Test
	public void testcercaRel_Dichiarazione_PrecDichiara() {
		Rel_Dichiarazione_PrecDichiaraSearch as = new Rel_Dichiarazione_PrecDichiaraSearch();
		as.setIddichiarazione(1);
		tariManager.cercaRel_Dichiarazione_PrecDichiara(as);
	}

	@Test
	public void testupdateRel_Dichiarazione_PrecDichiara() {
		Rel_Dichiarazione_PrecDichiara a = new Rel_Dichiarazione_PrecDichiara();
		a.setIddichiarazione(1);
		a.setIdprecedentedichiarazione(3);
		tariManager.updateRel_Dichiarazione_PrecDichiara(a);
	}

	@Test
	public void testdeleteRel_Dichiarazione_PrecDichiara() {
		Rel_Dichiarazione_PrecDichiara a = new Rel_Dichiarazione_PrecDichiara();
		a.setIddichiarazione(1);
		a.setIdprecedentedichiarazione(1);
		tariManager.deleteRel_Dichiarazione_PrecDichiara(a);
	}

	@Test
	public void testinserisciRel_Immobile_Locale() {
		Rel_Immobile_Locale a = new Rel_Immobile_Locale();
		a.setIdimmobile(3);
		a.setIdlocale(1);
		tariManager.inserisciRel_Immobile_Locale(a);
	}

	@Test
	public void testcercaRel_Immobile_Locale() {
		Rel_Immobile_LocaleSearch as = new Rel_Immobile_LocaleSearch();
		as.setIdimmobile(3);
		tariManager.cercaRel_Immobile_Locale(as);
	}

	@Test
	public void testupdateRel_Immobile_Locale() {
		Rel_Immobile_Locale a = new Rel_Immobile_Locale();
		a.setIdimmobile(3);
		a.setIdlocale(10);
		tariManager.updateRel_Immobile_Locale(a);
	}

	@Test
	public void testdeleteRel_Immobile_Locale() {
		Rel_Immobile_Locale a = new Rel_Immobile_Locale();
		a.setIdimmobile(3);
		a.setIdlocale(1);
		tariManager.deleteRel_Immobile_Locale(a);
	}

	@Test
	public void testinserisciRel_PersGiuridica_Dichiarazion() {
		Rel_PersGiuridica_Dichiarazion a = new Rel_PersGiuridica_Dichiarazion();
		a.setIddichiarazione(10);
		a.setIdpersonagiuridica(4);
		tariManager.inserisciRel_PersGiuridica_Dichiarazion(a);
	}

	@Test
	public void testcercaRel_PersGiuridica_Dichiarazion() {
		Rel_PersGiuridica_DichiarazionSearch as = new Rel_PersGiuridica_DichiarazionSearch();
		as.setIddichiarazione(10);
		tariManager.cercaRel_PersGiuridica_Dichiarazion(as);
	}

	@Test
	public void testupdateRel_PersGiuridica_Dichiarazion() {
		Rel_PersGiuridica_Dichiarazion a = new Rel_PersGiuridica_Dichiarazion();
		a.setIddichiarazione(10);
		a.setIdpersonagiuridica(6);
		tariManager.updateRel_PersGiuridica_Dichiarazion(a);
	}

	@Test
	public void testdeleteRel_PersGiuridica_Dichiarazion() {
		Rel_PersGiuridica_Dichiarazion a = new Rel_PersGiuridica_Dichiarazion();
		a.setIddichiarazione(10);
		a.setIdpersonagiuridica(4);
		tariManager.deleteRel_PersGiuridica_Dichiarazion(a);
	}

}
