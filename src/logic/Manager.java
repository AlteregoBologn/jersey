package logic;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import dao.CittaDao;
import dao.MedicoDao;
import dao.PersonaDao;
import dao.Rel_Medico_AmbulatorioDao;
import dao.Rel_Medico_VisitaDao;
import dao.Rel_Persona_EsenzioneDao;
import dao.Rel_Persona_MedicoDao;
import dao.Rel_Persona_VisitaDao;
import dao.Rel_Prescrizione_FarmacoDao;
import dao.Rel_Prescrizione_PrestazioneDao;
import dao.Rel_Visita_PrestazioneDao;
import model.Citta;
import model.CittaSearch;
import model.Medico;
import model.MedicoSearch;
import model.Persona;
import model.PersonaSearch;
import model.Rel_Medico_Ambulatorio;
import model.Rel_Medico_Visita;
import model.Rel_Persona_Esenzione;
import model.Rel_Persona_Medico;
import model.Rel_Persona_MedicoSearch;
import model.Rel_Persona_Visita;
import model.Rel_Prescrizione_Farmaco;
import model.Rel_Prescrizione_Prestazione;
import model.Rel_Visita_Prestazione;
import web.MySession;

public class Manager {

	@Autowired
	private PersonaDao personaDao;
	@Autowired
	private MedicoDao medicoDao;
	@Autowired
	private Rel_Persona_EsenzioneDao relPersonaEsenzioneDao;
	@Autowired
	private Rel_Persona_MedicoDao relPersonaMedicoDao;
	@Autowired
	private Rel_Medico_AmbulatorioDao relMedicoAmbulatorioDao;
	@Autowired
	private Rel_Persona_VisitaDao relPersonaVisitaDao;
	@Autowired
	private Rel_Medico_VisitaDao relMedicoVisitaDao;
	@Autowired
	private Rel_Visita_PrestazioneDao relVisitaPrestazioneDao;
	@Autowired
	private Rel_Prescrizione_FarmacoDao relPrescrizioneFarmacoDao;
	@Autowired
	private Rel_Prescrizione_PrestazioneDao relPrescrizionePrestazioneDao;
	@Autowired
	private CittaDao cittaDao;
	@Autowired
	private DataSource dataSource;

	public void testConn() throws SQLException {
		dataSource.getConnection().close();
	}

	public void registraPersona(Persona p) {
		personaDao.insert(p);
	}
	
	public void cambiaPassword(Persona p)  {
		personaDao.cambiaPassword(p);
	}

	public Persona login(String username, String pwd) {
		PersonaSearch ps = new PersonaSearch();
		ps.setEmail(username);
		ps.setPassword(pwd);
		ps.setCanc("N");
		List<Persona> Trovati = personaDao.cerca(ps);
		if (Trovati.isEmpty())
			throw new RuntimeException("Login errato - utente non registrato");
		if (Trovati.size() > 1)
			throw new RuntimeException("Login errato - piu utenti con email e stessa pwd");
		else 	
		{
			MySession.get().setUtente((Persona)Trovati.get(0));
			return (Persona) Trovati.get(0);
		}
	}

	public void logout(Persona persona) {
		
	}

	public List<Persona> cercaPersone(PersonaSearch ps) {
		return personaDao.cerca(ps);
	}
	
	public Integer countPersone(PersonaSearch ps) {
		return personaDao.count(ps);
	}
	
	public List<Medico> cercaMedico(MedicoSearch ms) {
		return medicoDao.cerca(ms);
	}
	
	public Integer countMedico(MedicoSearch ms) {
		return medicoDao.count(ms);
	}
	
	public List<Rel_Persona_Medico> cercaPersonaMedico(Rel_Persona_MedicoSearch s) {
		return relPersonaMedicoDao.loadAll(s);
	}
	
	public void deleteMedico(Medico m) {
		medicoDao.delete(m);
	}
	public void deRegistraPersona(Persona p) {
		p.setCanc("S");
		personaDao.update(p);
	}

	public void registraMedico(Medico m) {
		MedicoSearch ms = new MedicoSearch();
		ms.setCf(m.getCf());
		ms.setCanc("N");
		List<Medico> ret = cercaMedico(ms);
		if(ret.isEmpty()){
			m.setCanc("N");
			medicoDao.insert(m);
		} else {
			throw new RuntimeException("Medico con CF "+m.getCf()+" esiste già");
		}
		
	}

	public void inserisciRel_Persona_Esenzione(Rel_Persona_Esenzione a) {
		relPersonaEsenzioneDao.insert(a);
	}

	public void deleteRel_Persona_Esenzione(Rel_Persona_Esenzione a) {
		relPersonaEsenzioneDao.delete(a);
	}

	public void inserisciRel_Persona_Medico(Rel_Persona_Medico a)
	{
		relPersonaMedicoDao.insert(a);
	}

	public void deleteRel_Persona_Medico(Rel_Persona_Medico a) {
		relPersonaMedicoDao.delete(a);
	}

	public void inserisciRel_Medico_Ambulatorio(Rel_Medico_Ambulatorio a)
	{
		relMedicoAmbulatorioDao.insert(a);
	}

	public void eliminaRel_Medico_Ambulatorio(Rel_Medico_Ambulatorio a) {
		relMedicoAmbulatorioDao.delete(a);
	}
	public void inserisciRel_Persona_Visita(Rel_Persona_Visita a)
	{
		relPersonaVisitaDao.insert(a);
	}

	public void deleteRel_Persona_Visita(Rel_Persona_Visita a) {
		relPersonaVisitaDao.delete(a);
	}

	public void inserisciRel_Medico_Visita(Rel_Medico_Visita a)
	{
		relMedicoVisitaDao.insert(a);
	}
	public void deleteRel_Medico_Visita(Rel_Medico_Visita a) {
		relMedicoVisitaDao.delete(a);
	}

	public void inserisciRel_Visita_Prestazione(Rel_Visita_Prestazione a)
	{
		relVisitaPrestazioneDao.insert(a);
	}

	public void deleteRel_Visita_Prestazione(Rel_Visita_Prestazione a) {
		relVisitaPrestazioneDao.delete(a);
	}
	public void inserisciRel_Prescrizione_Farmaco(Rel_Prescrizione_Farmaco a)
	{
		relPrescrizioneFarmacoDao.insert(a);
	}
	public void deleteRel_Prescrizione_Farmaco(Rel_Prescrizione_Farmaco a) {
		relPrescrizioneFarmacoDao.delete(a);
	}
	public void inserisciRel_Prescrizione_Prestazione(Rel_Prescrizione_Prestazione a)
	{
		relPrescrizionePrestazioneDao.insert(a);
	}
	public void deleteRel_Prescrizione_Prestazione(Rel_Prescrizione_Prestazione a) {
		relPrescrizionePrestazioneDao.delete(a);
	}
	
	public List<Citta> cercaCitta (CittaSearch cs) {
		return cittaDao.loadAll(cs);
	}

	public String save() {
		return "Jersey + Spring example";
	}

	public String getVersion() {
		return "1.0.0";
	}

}
