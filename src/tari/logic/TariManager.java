package tari.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.DecodificaDao;
import dao.IndirizzoDao;
import dao.Rel_Persona_IndirizzoDao;
import model.Decodifica;
import model.DecodificaSearch;
import model.Indirizzo;
import model.IndirizzoSearch;
import model.Rel_Persona_Indirizzo;
import model.Rel_Persona_IndirizzoSearch;
import tari.dao.AllegatoDao;
import tari.dao.DichiarazioneDao;
import tari.dao.ImmobileDao;
import tari.dao.LocaleDao;
import tari.dao.PersonaGiuridicaDao;
import tari.dao.PersonaTariDao;
import tari.dao.PrecedenteDichiarazioneDao;
import tari.dao.relationDao.Rel_Dichiarazione_ImmobileDao;
import tari.dao.relationDao.Rel_Dichiarazione_PrecDichiaraDao;
import tari.dao.relationDao.Rel_Immobile_LocaleDao;
import tari.dao.relationDao.Rel_PersGiur_DichiarazioneDao;
import tari.dao.relationDao.Rel_PersonaGiuridica_AllegatoDao;
import tari.dao.relationDao.Rel_PersonaTari_AllegatoDao;
import tari.dao.relationDao.Rel_PersonaTari_PersonaGiuridicaDao;
import tari.dao.relationDao.Rel_Persona_DichiarazioneDao;
import tari.model.Allegato;
import tari.model.AllegatoSearch;
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
import tari.model.relationModel.Rel_PersGiur_Dichiarazione;
import tari.model.relationModel.Rel_PersGiur_DichiarazioneSearch;
import tari.model.relationModel.Rel_PersGiur_Dichiarazione;
import tari.model.relationModel.Rel_PersGiur_DichiarazioneSearch;
import tari.model.relationModel.Rel_PersonaGiuridica_Allegato;
import tari.model.relationModel.Rel_PersonaGiuridica_AllegatoSearch;
import tari.model.relationModel.Rel_PersonaTari_Allegato;
import tari.model.relationModel.Rel_PersonaTari_AllegatoSearch;
import tari.model.relationModel.Rel_PersonaTari_Dichiarazione;
import tari.model.relationModel.Rel_PersonaTari_DichiarazioneSearch;
import tari.model.relationModel.Rel_PersonaTari_PersonaGiuridica;
import tari.model.relationModel.Rel_PersonaTari_PersonaGiuridicaSearch;

public class TariManager {

	@Autowired
	private AllegatoDao allegatoDao;

	@Autowired
	private ImmobileDao immobileDao;

	@Autowired
	private LocaleDao localeDao;

	@Autowired
	private DichiarazioneDao dichiarazioneDao;

	@Autowired
	private PrecedenteDichiarazioneDao precedentedichiarazioneDao;

	@Autowired
	private PersonaGiuridicaDao personagiuridicaDao;

	@Autowired
	private PersonaTariDao personaTariDao;

	@Autowired
	private Rel_Dichiarazione_ImmobileDao relDichiarazioneImmobileDao;

	@Autowired
	private Rel_Persona_DichiarazioneDao relPersonaDichiarazioneDao;

	@Autowired
	private Rel_Dichiarazione_PrecDichiaraDao relDichiarazionePrecdichiaraDao;

	@Autowired
	private Rel_Immobile_LocaleDao relImmobileLocaleDao;

	@Autowired
	private Rel_PersGiur_DichiarazioneDao relPersgiuridicaDichiarazioneDao;
	
	@Autowired
	private Rel_PersonaTari_PersonaGiuridicaDao relPersonaTariPersonaGiuridicaDao;
	
	@Autowired
	private Rel_PersonaTari_AllegatoDao relPersonaTariAllegatoDao;
	
	@Autowired
	private Rel_PersonaGiuridica_AllegatoDao relPersonaGiuridicaAllegatoDao;

	@Autowired
	private DecodificaDao decodificaDao;

	@Autowired
	private Rel_Persona_IndirizzoDao relPersonaIndirizzoDao;

	@Autowired
	private IndirizzoDao indirizzoDao;
	
	
	
	/******************************* PERSONA TARI ********************************************/

	
	public void insertPersonaTari(PersonaTari personaTari) {
		personaTariDao.insert(personaTari);
	}

	public void updatePersonaTari(PersonaTari personaTari) {
		personaTariDao.update(personaTari);
	}
	
	public void deletePersonaTari(PersonaTari a) {
		personaTariDao.delete(a);
	}

	public List<PersonaTari> cercaPersonaTari(PersonaTariSearch as) {
		return personaTariDao.loadAll(as);
	}

	
	
	/******************************* IMMOBILE ********************************************/
	
	
	public void inserisciImmobile(Immobile a) {
		immobileDao.insert(a);
	}

	public void updateImmobile(Immobile a) {
		immobileDao.update(a);
	}

	public void deleteImmobile(Immobile a) {
		immobileDao.delete(a);
	}

	public List<Immobile> cercaImmobile(ImmobileSearch as) {
		return immobileDao.loadAll(as);
	}

	
	
	/******************************* LOCALE ********************************************/
	
	
	public void inserisciLocale(Locale a) {
		localeDao.insert(a);
	}

	public void updateLocale(Locale a) {
		localeDao.update(a);
	}

	public void deleteLocale(Locale a) {
		localeDao.delete(a);
	}
	
	public List<Locale> cercaLocale(LocaleSearch as) {
		return localeDao.loadAll(as);
	}

	

	/******************************* DICHIARAZIONE ********************************************/
	
	
	public void inserisciDichiarazione(Dichiarazione a) {
		dichiarazioneDao.insert(a);
	}

	public void updateDichiarazione(Dichiarazione a) {
		dichiarazioneDao.update(a);
	}
	
	public void deleteDichiarazione(Dichiarazione a) {
		dichiarazioneDao.delete(a);
	}
	
	public List<Dichiarazione> cercaDichiarazione(DichiarazioneSearch as) {
		return dichiarazioneDao.loadAll(as);
	}

	public void inserisciPrecedenteDichiarazione(PrecedenteDichiarazione a) {
		precedentedichiarazioneDao.insert(a);
	}

	public List<PrecedenteDichiarazione> cercaPrecedenteDichiarazione(PrecedenteDichiarazioneSearch as) {
		return precedentedichiarazioneDao.loadAll(as);
	}

	public void updatePrecedenteDichiarazione(PrecedenteDichiarazione a) {
		precedentedichiarazioneDao.update(a);
	}

	public void deletePrecedenteDichiarazione(PrecedenteDichiarazione a) {
		precedentedichiarazioneDao.delete(a);
	}

	
	/******************************* PERSONA GIURIDICA ********************************************/
	
	
	public void insertPersonaGiuridica(PersonaGiuridica personaGiuridica) {
		personagiuridicaDao.insert(personaGiuridica);
	}

	public void updatePersonaGiuridica(PersonaGiuridica personaGiuridica){
		personagiuridicaDao.update(personaGiuridica);
	}
	
	public void deletePersonaGiuridica(PersonaGiuridica a) {
		personagiuridicaDao.delete(a);
	}
	
	public List<PersonaGiuridica> cercaPersonaGiuridica(PersonaGiuridicaSearch as) {
		return personagiuridicaDao.loadAll(as);
	}

	public void insertRel_PersGiuridica_Dichiarazione(Rel_PersGiur_Dichiarazione rel_PersGiur_Dichiarazione) {
		relPersgiuridicaDichiarazioneDao.insert(rel_PersGiur_Dichiarazione);
	}

	public List<Rel_PersGiur_Dichiarazione> loadRel_PersGiuridica_Dichiarazione(
			Rel_PersGiur_DichiarazioneSearch rel_PersGiur_DichiarazioneSearch) {
		return relPersgiuridicaDichiarazioneDao.loadAll(rel_PersGiur_DichiarazioneSearch);
	}

	public void updateRel_PersGiuridica_Dichiarazione(Rel_PersGiur_Dichiarazione rel_PersGiur_Dichiarazione) {
		relPersgiuridicaDichiarazioneDao.update(rel_PersGiur_Dichiarazione);
	}

	public void deleteRel_PersGiuridica_Dichiarazion(Rel_PersGiur_Dichiarazione rel_PersGiur_Dichiarazione) {
		relPersgiuridicaDichiarazioneDao.delete(rel_PersGiur_Dichiarazione);
	}

	
	
	

	public void inseriscirel_Dichiarazione_Immobile(Rel_Dichiarazione_Immobile a) {
		relDichiarazioneImmobileDao.insert(a);
	}

	public List<Rel_Dichiarazione_Immobile> cercarel_Dichiarazione_Immobile(Rel_Dichiarazione_ImmobileSearch as) {
		return relDichiarazioneImmobileDao.loadAll(as);
	}

	public void deleterel_Dichiarazione_Immobile(Rel_Dichiarazione_Immobile a) {
		relDichiarazioneImmobileDao.delete(a);
	}

	public void inserisciRel_Persona_Dichiarazione(Rel_PersonaTari_Dichiarazione a) {
		relPersonaDichiarazioneDao.insert(a);
	}

	public List<Rel_PersonaTari_Dichiarazione> cercaRel_Persona_Dichiarazione(Rel_PersonaTari_DichiarazioneSearch as) {
		return relPersonaDichiarazioneDao.loadAll(as);
	}

	public void deleteRel_Persona_Dichiarazione(Rel_PersonaTari_Dichiarazione a) {
		relPersonaDichiarazioneDao.delete(a);
	}

	public void inserisciRel_Dichiarazione_PrecDichiara(Rel_Dichiarazione_PrecDichiara a) {
		relDichiarazionePrecdichiaraDao.insert(a);
	}

	public List<Rel_Dichiarazione_PrecDichiara> cercaRel_Dichiarazione_PrecDichiara(
			Rel_Dichiarazione_PrecDichiaraSearch as) {
		return relDichiarazionePrecdichiaraDao.loadAll(as);
	}

	public void updateRel_Dichiarazione_PrecDichiara(Rel_Dichiarazione_PrecDichiara a) {
		relDichiarazionePrecdichiaraDao.update(a);
	}

	public void deleteRel_Dichiarazione_PrecDichiara(Rel_Dichiarazione_PrecDichiara a) {
		relDichiarazionePrecdichiaraDao.delete(a);
	}

	public void inserisciRel_Immobile_Locale(Rel_Immobile_Locale a) {
		relImmobileLocaleDao.insert(a);
	}

	public List<Rel_Immobile_Locale> cercaRel_Immobile_Locale(Rel_Immobile_LocaleSearch as) {
		return relImmobileLocaleDao.loadAll(as);
	}

	public void updateRel_Immobile_Locale(Rel_Immobile_Locale a) {
		relImmobileLocaleDao.update(a);
	}

	public void deleteRel_Immobile_Locale(Rel_Immobile_Locale a) {
		relImmobileLocaleDao.delete(a);
	}

	
	public List<Decodifica> cercaDecodifica(DecodificaSearch ds) {
		return decodificaDao.loadAll(ds);
	}
	
	
	
	/******************************* INDIRIZZO ********************************************/
	
	
	public void inserisciIndirizzo(Indirizzo a) {
		indirizzoDao.insert(a);
	}
	
	public void updateIndirizzo(Indirizzo a) {
		indirizzoDao.update(a);
	}

	public void deleteIndirizzo(Indirizzo a) {
		indirizzoDao.delete(a);
	}
	
	public List<Indirizzo> cercaIndirizzo(IndirizzoSearch as) {
		return indirizzoDao.loadAll(as);
	}

	public void inserisciRel_Persona_Indirizzo(Rel_Persona_Indirizzo a) {
		relPersonaIndirizzoDao.insert(a);
	}

	public List<Rel_Persona_Indirizzo> cercaRel_Persona_Indirizzo(Rel_Persona_IndirizzoSearch as) {
		return relPersonaIndirizzoDao.loadAll(as);
	}

	public void updateRel_Persona_Indirizzo(Rel_Persona_Indirizzo a) {
		relPersonaIndirizzoDao.update(a);
	}

	public void deleteRel_Persona_Indirizzo(Rel_Persona_Indirizzo a) {
		relPersonaIndirizzoDao.delete(a);
	}

	

	/******************************* RELAZIONE PERSONA TARI CON PERSONA GIURIDICA ********************************************/

	
	public List<Rel_PersonaTari_PersonaGiuridica> searchRel_PersonaTari_PersonaGiuridica(Rel_PersonaTari_PersonaGiuridicaSearch relPersonaTariPersonaGiuridicaSearch) {
		return relPersonaTariPersonaGiuridicaDao.loadAll(relPersonaTariPersonaGiuridicaSearch);
	}
	
	public void insertRel_PersonaTari_PersonaGiuridica(Rel_PersonaTari_PersonaGiuridica rel_PersonaTari_PersonaGiuridica) {
		relPersonaTariPersonaGiuridicaDao.insert(rel_PersonaTari_PersonaGiuridica);
	}
	
	public void updateRel_PersonaTari_PersonaGiuridica(Rel_PersonaTari_PersonaGiuridica rel_PersonaTari_PersonaGiuridica) {
		relPersonaTariPersonaGiuridicaDao.update(rel_PersonaTari_PersonaGiuridica);
	}
	
	public void deleteRel_PersonaTari_PersonaGiuridica(Rel_PersonaTari_PersonaGiuridica rel_PersonaTari_PersonaGiuridica) {
		relPersonaTariPersonaGiuridicaDao.delete(rel_PersonaTari_PersonaGiuridica);
	}
	
	
	
	/******************************* ALLEGATO ********************************************/
	
	
	public void insertAllegato(Allegato allegato) {
		allegatoDao.insert(allegato);
	}

	public void updateAllegato(Allegato allegato) {
		allegatoDao.update(allegato);
	}

	public void deleteAllegato(Allegato allegato) {
		allegatoDao.delete(allegato);
	}

	public List<Allegato> searchAllegato(AllegatoSearch allegatoSearch) {
		return allegatoDao.loadAll(allegatoSearch);
	}
	
	public List<Rel_PersonaTari_Allegato> searchRel_PersonaTari_Allegato(Rel_PersonaTari_AllegatoSearch relPersonaTariAllegatoSearch) {
		return relPersonaTariAllegatoDao.loadAll(relPersonaTariAllegatoSearch);
	}
	
	public void insertRel_PersonaTari_Allegato(Rel_PersonaTari_Allegato rel_PersonaTari_Allegato) {
		relPersonaTariAllegatoDao.insert(rel_PersonaTari_Allegato);
	}
	
	public void updateRel_PersonaTari_Allegato(Rel_PersonaTari_Allegato rel_PersonaTari_Allegato) {
		relPersonaTariAllegatoDao.update(rel_PersonaTari_Allegato);
	}
	
	public void deleteRel_PersonaTari_Allegato(Rel_PersonaTari_Allegato rel_PersonaTari_Allegato) {
		relPersonaTariAllegatoDao.delete(rel_PersonaTari_Allegato);
	}

	public List<Rel_PersonaGiuridica_Allegato> searchRel_PersonaGiuridica_Allegato(Rel_PersonaGiuridica_AllegatoSearch relPersonaGiuridicaAllegatoSearch) {
		return relPersonaGiuridicaAllegatoDao.loadAll(relPersonaGiuridicaAllegatoSearch);
	}
	
	public void insertRel_PersonaGiuridica_Allegato(Rel_PersonaGiuridica_Allegato rel_PersonaGiuridica_Allegato) {
		relPersonaGiuridicaAllegatoDao.insert(rel_PersonaGiuridica_Allegato);
	}
	
	public void updateRel_PersonaGiuridica_Allegato(Rel_PersonaGiuridica_Allegato rel_PersonaGiuridica_Allegato) {
		relPersonaGiuridicaAllegatoDao.update(rel_PersonaGiuridica_Allegato);
	}
	
	public void deleteRel_PersonaGiuridica_Allegato(Rel_PersonaGiuridica_Allegato rel_PersonaGiuridica_Allegato) {
		relPersonaGiuridicaAllegatoDao.delete(rel_PersonaGiuridica_Allegato);
	}
	
}

