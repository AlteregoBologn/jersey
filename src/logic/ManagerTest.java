package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Codifica;
import model.CodificaSearch;
import model.Esenzione;
import model.Medico;
import model.MedicoSearch;
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
		a.setDataA(new Date());
		a.setDataDa(new Date());
		a.setIdesenzione("2");
		a.setIdpersona("58");
		manager.inserisciRel_Persona_Esenzione(a);
		System.out.println(a);
	}

	@Test
	public void testeliminaRel_Persona_Esenzione()
	{
		Rel_Persona_Esenzione a = new Rel_Persona_Esenzione();
		a.setIdpersona("29");
		manager.deleteRel_Persona_Esenzione(a);
		System.out.println(a);
	}

	@Test
	public void testinserisciRel_Persona_Medico() {
		Rel_Persona_Medico a = new Rel_Persona_Medico();
		a.setDataA(new Date());
		a.setDataDa(new Date());
		a.setIdmedico(81);
		a.setIdpersona(58);
		manager.inserisciRel_Persona_Medico(a);
		System.out.println(a);
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
	
	@Test
	public void testcercaMedico() {
		MedicoSearch ms = new MedicoSearch();
		ms.setUnid(79);
		List<Medico> medici = manager.cercaMedico(ms);
		if(medici.isEmpty())
			throw new RuntimeException();
		System.out.println(medici.get(0).toString());	
	}
	
	@Test
	public void testcountMedico() {
		MedicoSearch ms = new MedicoSearch();
		ms.setUnid(79);
		int count = manager.countMedico(ms);
		System.out.println(count);
	}
	
	@Test
	public void testsalvaEsenzione(){
		Esenzione esenzione = new Esenzione();
		esenzione.setUnid(5);
		esenzione.setDescrizione("diagnosi precoce");
		esenzione.setOperation(esenzione.OP_INSERT);
		manager.insertEsenzione(esenzione);
	}
	
	
	/************************* CODIFICA *************************/
	
	@Test
	public void testInsertCodifica(){
		Codifica codifica = new Codifica();
		codifica.setUnid(10);
		codifica.setTipo("straordinaria");
		codifica.setDescrizione("prova di insert");
		manager.insertCodifica(codifica);
		
	}
	
	@Test
	public void testUpdateCodifica(){
		CodificaSearch cs = new CodificaSearch();
		cs.setUnid(2);
		List<Codifica> listaCodifiche = manager.loadCodifica(cs);
		if (listaCodifiche.isEmpty()){
			throw new RuntimeException();
		}
		listaCodifiche.get(0).setDescrizione("prova di update");
		manager.updateCodifica(listaCodifiche.get(0));
	}
	
	@Test
	public void testDeleteCodifica(){
		CodificaSearch cs = new CodificaSearch();
		cs.setUnid(2);
		List<Codifica> listaCodifiche = manager.loadCodifica(cs);
		if (listaCodifiche.isEmpty()){
			throw new RuntimeException();
		}
		manager.deleteCodifica(listaCodifiche.get(0));
		
	}
	
	@Test
	public void testLoadCodifica(){
		CodificaSearch cs = new CodificaSearch();
		cs.setUnid(1);
		List<Codifica> listaCodifiche = manager.loadCodifica(cs);
		if(listaCodifiche.isEmpty())
			throw new RuntimeException();
		System.out.println(listaCodifiche.get(0).toString()); 
		
	}
	
	@Test
	public void countCodifica(){
		CodificaSearch cs = new CodificaSearch();
		int count = manager.countCodifica(cs);
		System.out.println("Il numero delle codifiche è: "+count);
		
	}
}
