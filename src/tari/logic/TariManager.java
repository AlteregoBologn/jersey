package tari.logic;

import org.springframework.beans.factory.annotation.Autowired;

import tari.dao.DichiarazioneDao;
import tari.dao.ImmobileDao;
import tari.dao.LocaleDao;
import tari.dao.PersonaGiuridicaDao;
import tari.dao.PrecedenteDichiarazioneDao;
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


	public void inserisciImmobile(Immobile a)
	{
		immobileDao.insert(a);
	}
	public void cercaImmobile(ImmobileSearch as) {
		immobileDao.loadAll(as);
	}
	public void deleteImmobile(Immobile a) {
		immobileDao.delete(a);
	}
	
	public void inserisciLocale(Locale a)
	{
	  localeDao.insert(a);
	}

	public void cercaLocale(LocaleSearch as) {
	  localeDao.loadAll(as);
	}

	public void deleteLocale(Locale a) {
	  localeDao.delete(a);
	}
	

	public void inserisciDichiarazione(Dichiarazione a)
	{
	  dichiarazioneDao.insert(a);
	}
	
	public void cercaDichiarazione(DichiarazioneSearch as) {
	  dichiarazioneDao.loadAll(as);
	}

	public void deleteDichiarazione(Dichiarazione a) {
	  dichiarazioneDao.delete(a);
	}
	

	public void inserisciPrecedenteDichiarazione(PrecedenteDichiarazione a)
	{
	  precedentedichiarazioneDao.insert(a);
	}

	public void cercaPrecedenteDichiarazione(PrecedenteDichiarazioneSearch as) {
	  precedentedichiarazioneDao.loadAll(as);
	}

	public void deletePrecedenteDichiarazione(PrecedenteDichiarazione a) {
	  precedentedichiarazioneDao.delete(a);
	}


	public void inserisciPersonaGiuridica(PersonaGiuridica a)
	{
	  personagiuridicaDao.insert(a);
	}

	public void cercaPersonaGiuridica(PersonaGiuridicaSearch as) {
	  personagiuridicaDao.loadAll(as);
	}

	public void deletePersonaGiuridica(PersonaGiuridica a) {
	  personagiuridicaDao.delete(a);
	}

}

