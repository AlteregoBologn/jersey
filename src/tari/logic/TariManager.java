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
import tari.dao.DichiarazioneDao;
import tari.dao.ImmobileDao;
import tari.dao.LocaleDao;
import tari.dao.PersonaGiuridicaDao;
import tari.dao.PersonaTariDao;
import tari.dao.PrecedenteDichiarazioneDao;
import tari.dao.relationDao.Rel_Dichiarazione_ImmobileDao;
import tari.dao.relationDao.Rel_Dichiarazione_PrecDichiaraDao;
import tari.dao.relationDao.Rel_Immobile_LocaleDao;
import tari.dao.relationDao.Rel_PersGiuridica_DichiarazionDao;
import tari.dao.relationDao.Rel_Persona_DichiarazioneDao;
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

public class TariManager {

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
	private Rel_PersGiuridica_DichiarazionDao relPersgiuridicaDichiarazionDao;
	
	@Autowired
	private DecodificaDao decodificaDao;
	
	@Autowired
	private Rel_Persona_IndirizzoDao relPersonaIndirizzoDao;
	
	@Autowired
	private IndirizzoDao indirizzoDao;
	
	public void inserisciPersonaTari(PersonaTari a)
	{
		personaTariDao.insert(a);
	}
	public void updatePersonaTari(PersonaTari a)
	{
		personaTariDao.update(a);
	}
	public List<PersonaTari> cercaPersonaTari(PersonaTariSearch as) {
		return personaTariDao.loadAll(as);
	}
	public void deletePersonaTari(PersonaTari a)
	{
		personaTariDao.delete(a);
	}
	public void inserisciImmobile(Immobile a)
	{
		immobileDao.insert(a);
	}
	public void updateImmobile(Immobile a)
	{
		immobileDao.update(a);
	}
	public List<Immobile> cercaImmobile(ImmobileSearch as) {
		return immobileDao.loadAll(as);
	}
	public void deleteImmobile(Immobile a) {
		immobileDao.delete(a);
	}

	public void inserisciLocale(Locale a)
	{
		localeDao.insert(a);
	}
	
	public void updateLocale(Locale a)
	{
		localeDao.update(a);
	}

	public List<Locale> cercaLocale(LocaleSearch as) {
		return localeDao.loadAll(as);
	}

	public void deleteLocale(Locale a) {
		localeDao.delete(a);
	}


	public void inserisciDichiarazione(Dichiarazione a)
	{
		dichiarazioneDao.insert(a);
	}

	public List<Dichiarazione> cercaDichiarazione(DichiarazioneSearch as) {
		return dichiarazioneDao.loadAll(as);
	}

	public void updateDichiarazione(Dichiarazione a) {
		dichiarazioneDao.update(a);
	}

	public void deleteDichiarazione(Dichiarazione a) {
		dichiarazioneDao.delete(a);
	}


	public void inserisciPrecedenteDichiarazione(PrecedenteDichiarazione a)
	{
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


	public void inserisciPersonaGiuridica(PersonaGiuridica a)
	{
		personagiuridicaDao.insert(a);
	}

	public List<PersonaGiuridica> cercaPersonaGiuridica(PersonaGiuridicaSearch as) {
		return personagiuridicaDao.loadAll(as);
	}

	public void deletePersonaGiuridica(PersonaGiuridica a) {
		personagiuridicaDao.delete(a);
	}

	public void inseriscirel_Dichiarazione_Immobile(Rel_Dichiarazione_Immobile a)
	{
		relDichiarazioneImmobileDao.insert(a);
	}

	public List<Rel_Dichiarazione_Immobile> cercarel_Dichiarazione_Immobile(Rel_Dichiarazione_ImmobileSearch as) {
		return relDichiarazioneImmobileDao.loadAll(as);
	}

	public void deleterel_Dichiarazione_Immobile(Rel_Dichiarazione_Immobile a) {
		relDichiarazioneImmobileDao.delete(a);
	}

	public void inserisciRel_Persona_Dichiarazione(Rel_PersonaTari_Dichiarazione a)
	{
		relPersonaDichiarazioneDao.insert(a);
	}

	public List<Rel_PersonaTari_Dichiarazione> cercaRel_Persona_Dichiarazione(Rel_PersonaTari_DichiarazioneSearch as) {
		return relPersonaDichiarazioneDao.loadAll(as);
	}

	public void deleteRel_Persona_Dichiarazione(Rel_PersonaTari_Dichiarazione a) {
		relPersonaDichiarazioneDao.delete(a);
	}

	public void inserisciRel_Dichiarazione_PrecDichiara(Rel_Dichiarazione_PrecDichiara a)
	{
		relDichiarazionePrecdichiaraDao.insert(a);
	}

	public List<Rel_Dichiarazione_PrecDichiara> cercaRel_Dichiarazione_PrecDichiara(Rel_Dichiarazione_PrecDichiaraSearch as) {
		return relDichiarazionePrecdichiaraDao.loadAll(as);
	}

	public void updateRel_Dichiarazione_PrecDichiara(Rel_Dichiarazione_PrecDichiara a) {
		relDichiarazionePrecdichiaraDao.update(a);
	}


	public void deleteRel_Dichiarazione_PrecDichiara(Rel_Dichiarazione_PrecDichiara a) {
		relDichiarazionePrecdichiaraDao.delete(a);
	}
	

	public void inserisciRel_Immobile_Locale(Rel_Immobile_Locale a)
	{
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

	public void inserisciRel_PersGiuridica_Dichiarazion(Rel_PersGiuridica_Dichiarazion a)
	{
	  relPersgiuridicaDichiarazionDao.insert(a);
	}

	public List<Rel_PersGiuridica_Dichiarazion> cercaRel_PersGiuridica_Dichiarazion(Rel_PersGiuridica_DichiarazionSearch as) {
	  return relPersgiuridicaDichiarazionDao.loadAll(as);
	}

	public void updateRel_PersGiuridica_Dichiarazion(Rel_PersGiuridica_Dichiarazion a) {
	  relPersgiuridicaDichiarazionDao.update(a);
	}

	public void deleteRel_PersGiuridica_Dichiarazion(Rel_PersGiuridica_Dichiarazion a) {
	  relPersgiuridicaDichiarazionDao.delete(a);
	}
	
	public List<Decodifica> cercaDecodifica(DecodificaSearch ds){
		return decodificaDao.loadAll(ds);
	}

	public void inserisciRel_Persona_Indirizzo(Rel_Persona_Indirizzo a)
	{
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
	
	public void inserisciIndirizzo(Indirizzo a)
	{
		indirizzoDao.insert(a);
	}

	public List<Indirizzo> cercaIndirizzo(IndirizzoSearch as) {
		return indirizzoDao.loadAll(as);
	}

	public void updateIndirizzo(Indirizzo a) {
		indirizzoDao.update(a);
	}

	public void deleteIndirizzo(Indirizzo a) {
		indirizzoDao.delete(a);
	}

}

