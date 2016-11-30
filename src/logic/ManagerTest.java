package logic;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Citta;
import model.CittaSearch;
import model.Medico;
import model.Persona;
import model.Rel_Medico_Ambulatorio;
import model.Rel_Medico_Visita;
import model.Rel_Persona_Esenzione;
import model.Rel_Persona_Medico;
import model.Rel_Persona_MedicoSearch;
import model.Rel_Persona_Visita;
import model.Rel_Prescrizione_Farmaco;
import model.Rel_Prescrizione_Prestazione;
import model.Rel_Visita_Prestazione;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:context.xml"})
public class ManagerTest
{
	@Autowired
	private Manager manager;
	Logger log;

	public ManagerTest()
	{
		log = Logger.getAnonymousLogger();
	}

	@Test
	public void testRegistraPersona()
	{
		Persona p = new Persona();
		p.setNome("nome");
		p.setCognome("cognome");
		p.setEmail("a");
		p.setPassword("b");
		p.setCf("cf");
		p.setUnid(null);
		manager.registraPersona(p);
	}
	
	@Test
	public void testCercaCitta()
	{
		CittaSearch cs = new CittaSearch();
		cs.setNomeCittaLike("bo");
		List<Citta> ret = manager.cercaCitta(cs);
		System.out.println(ret);
		if(ret.isEmpty()) throw new RuntimeException("non va");
	}
	
	@Test
	public void testRegistraMedico()
	{
		Medico p = new Medico();
		p.setNome("nome");
		p.setCognome("cognome");
		p.setEmail("a");
		p.setPassword("b");
		p.setCf("cf2");
		p.setUnid(null);
		manager.registraMedico(p);
	}
	
	@Test
	public void testRegistraMedico2()
	{
		Medico p = new Medico();
		p.setNome("nome");
		p.setCognome("cognome");
		p.setEmail("a");
		p.setPassword("b");
		p.setCf("cf");
		p.setUnid(null);
		manager.registraMedico(p);
	}
	
	@Test
	public void testLogin()
	{
		manager.login("a", "b");
	}

	@Test
	public void testLogout()
	{
		Persona utente = manager.login("a", "b");
		manager.logout(utente);
	}

	@Test
	public void testCancellaPersona()
	{
		try
		{
			Persona p = manager.login("a", "b");
			manager.deRegistraPersona(p);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} 

	@Test
	public void testinserisciRel_Persona_Esenzione() 
	{
		Rel_Persona_Esenzione a = new Rel_Persona_Esenzione();
		a.setDataA(null);
		a.setDataDa(null);
		a.setIdesenzione("2");
		a.setIdpersona("58");
		manager.inserisciRel_Persona_Esenzione(a);
	}

	@Test
	public void testeliminaRel_Persona_Esenzione()
	{
		Rel_Persona_Esenzione a = new Rel_Persona_Esenzione();
		a.setIdpersona("29");
		manager.deleteRel_Persona_Esenzione(a);
	}

	@Test
	public void testinserisciRel_Persona_Medico() {
		Rel_Persona_Medico a = new Rel_Persona_Medico();
		//a.setDataA("09-OTT-2016");
		//a.setDataDa("06-GEN-1990");
		a.setIdmedico(17);
		a.setIdpersona(58);
		manager.inserisciRel_Persona_Medico(a);
	}

	@Test
	public void testdeleteRel_Persona_Medico()
	{
		Rel_Persona_Medico a = new Rel_Persona_Medico();
		a.setIdpersona(29);
		manager.deleteRel_Persona_Medico(a);
	}

	@Test
	public void testinserisciRel_Medico_Ambulatorio() {
		Rel_Medico_Ambulatorio a = new Rel_Medico_Ambulatorio();
		a.setDataA("09-OTT-2016");
		a.setDataDa("06-GEN-1990");
		a.setIdambulatorio("1");
		a.setIdmedico("17");
		manager.inserisciRel_Medico_Ambulatorio(a);
	}

	@Test
	public void testeliminaRel_Medico_Ambulatorio()
	{
		Rel_Medico_Ambulatorio a = new Rel_Medico_Ambulatorio();
		a.setIdambulatorio("1");
		manager.eliminaRel_Medico_Ambulatorio(a);
	}

	@Test
	public void testinserisciRel_Persona_Visita() {
		Rel_Persona_Visita a = new Rel_Persona_Visita();
		a.setIdpersona("73");
		a.setIdvisita("1");
		manager.inserisciRel_Persona_Visita(a);
	}

	@Test
	public void testdeleteRel_Persona_Visita() {
		Rel_Persona_Visita a = new Rel_Persona_Visita();
		a.setIdpersona("73");
		manager.deleteRel_Persona_Visita(a);
	}

	@Test
	public void testinserisciRel_Medico_Visita() {
		Rel_Medico_Visita a = new Rel_Medico_Visita();
		a.setIdmedico("17");
		a.setIdvisita("1");
		manager.inserisciRel_Medico_Visita(a);
	}

	@Test
	public void testdeleteRel_Medico_Visita() {
		Rel_Medico_Visita a = new Rel_Medico_Visita();
		a.setIdmedico("17");
		manager.deleteRel_Medico_Visita(a);
	}	

	@Test
	public void testinserisciRel_Visita_Prestazione() {
		Rel_Visita_Prestazione a = new Rel_Visita_Prestazione();
		a.setIdprestazione("1");
		a.setIdvisita("1");
		manager.inserisciRel_Visita_Prestazione(a);
	}

	@Test
	public void testdeleteRel_Visita_Prestazione() {
		Rel_Visita_Prestazione a = new Rel_Visita_Prestazione();
		a.setIdvisita("1");
		manager.deleteRel_Visita_Prestazione(a);
	}

	@Test
	public void testinserisciRel_Prescrizione_Farmaco() {
		Rel_Prescrizione_Farmaco a = new Rel_Prescrizione_Farmaco();
		a.setIdfarmaco("4");
		a.setIdprescrizione("6");
		manager.inserisciRel_Prescrizione_Farmaco(a);
	}

	@Test
	public void testdeleteRel_Prescrizione_Farmaco() {
		Rel_Prescrizione_Farmaco a = new Rel_Prescrizione_Farmaco();
		a.setIdprescrizione("6");
		manager.deleteRel_Prescrizione_Farmaco(a);
	}

	@Test
	public void testinserisciRel_Prescrizione_Prestazione() {
		Rel_Prescrizione_Prestazione a = new Rel_Prescrizione_Prestazione();
		a.setIdprescrizione("6");
		a.setIdprestazione("1");
		manager.inserisciRel_Prescrizione_Prestazione(a);
	}

	@Test
	public void testdeleteRel_Prescrizione_Prestazione() {
		Rel_Prescrizione_Prestazione a = new Rel_Prescrizione_Prestazione();
		a.setIdprestazione("1");
		manager.deleteRel_Prescrizione_Prestazione(a);
	}
}
