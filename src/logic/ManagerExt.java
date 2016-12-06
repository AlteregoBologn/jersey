package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Esenzione;
import model.EsenzioneSearch;
import model.Medico;
import model.MedicoSearch;
import model.Persona;
import model.PersonaSearch;
import model.Rel_Persona_Esenzione;
import model.Rel_Persona_EsenzioneSearch;
import model.Rel_Persona_Medico;
import model.Rel_Persona_MedicoSearch;
import modelExt.EsenzioneDiPersona;
import modelExt.MedicoDiPersona;
import modelExt.PersonaCompleta;

public class ManagerExt extends Manager {

	/************************ PERSONA *************************/

	public List<PersonaCompleta> loadPersoneCompleta(PersonaSearch ps) {
		List<PersonaCompleta> ret = new ArrayList();

		List<Persona> persone = cercaPersone(ps);
		for (Persona p : persone) {
			PersonaCompleta pc = new PersonaCompleta();
			
			pc.setOperation(pc.OP_UPDATE);
			pc.setPersona(p);
			loadMedici(p,pc);
			loadEsenzioni(p,pc);
			ret.add(pc);
		}
		return ret;
	}

	public PersonaCompleta loadPersonaCompleta(Integer unid) {
		PersonaSearch ps = new PersonaSearch();
		ps.setUnid(unid);
		List<PersonaCompleta> ret = loadPersoneCompleta(ps);
		if (ret.isEmpty())
			throw new RuntimeException("non trovo una persona con unid: " + unid);
		return ret.get(0);
	}

	private void savePersona(Persona p) {
		if (p.isInsert()) {
			insertPersona(p);
		} else if (p.isUpdate()) {
			updatePersona(p);
		} else if (p.isDelete()) {
			p.setCanc("S");
			updatePersona(p);
		}
	}

	public void savePersonaCompleta(PersonaCompleta pc) {
		if (pc.isInsert()) {
			savePersona(pc.getPersona());
			for (MedicoDiPersona m : pc.getMedici()) {
				salvaMedicoDiPersona(m,pc);
			}
			for(EsenzioneDiPersona e: pc.getEsenzioni()){
				salvaEsenzioneDiPersona(e,pc);
			}
		} 
		
		if (pc.isUpdate()){
			savePersona(pc.getPersona());
			for (MedicoDiPersona m : pc.getMedici()) {
				salvaMedicoDiPersona(m,pc);
			}
			for(EsenzioneDiPersona e: pc.getEsenzioni()){
				salvaEsenzioneDiPersona(e,pc);
			}
		}
		
		if (pc.isDelete()) {
			savePersona(pc.getPersona());
			for (MedicoDiPersona m : pc.getMedici()) {
				salvaMedicoDiPersona(m,pc);
			}
			for(EsenzioneDiPersona e: pc.getEsenzioni()){
				salvaEsenzioneDiPersona(e,pc);
			}
		}
		
		pc.setOperation(pc.OP_NOP);
	}

	/************************ MEDICO DI PERSONA *************************/
	
	
	public void loadMedici(Persona p, PersonaCompleta pc){
		
		Rel_Persona_MedicoSearch rpms = new Rel_Persona_MedicoSearch();
		rpms.setIdpersona(p.getUnid());
		List<Rel_Persona_Medico> rel_Persona_Medico = cercaRel_Persona_Medico(rpms);

		if (rel_Persona_Medico.isEmpty()){
			new RuntimeException("Non ci sono medici relativi alla persona");
		}
		else {
				for (Rel_Persona_Medico rpm : rel_Persona_Medico) {
					rpm.setOperation(pc.OP_UPDATE);
					MedicoSearch ms = new MedicoSearch();
					ms.setUnid(rpm.getIdmedico());
					List<Medico> medici = cercaMedico(ms);
					
					MedicoDiPersona m = new MedicoDiPersona();
					m.setOperation(pc.OP_UPDATE);
					m.setMedico(medici.get(0));
					m.setRelazione(rpm);
					pc.getMedici().add(m);
				}
			}
	}
	
	private void saveMedico(Medico m){
		if(m.isInsert()){
			insertMedico(m);
		} else if(m.isUpdate()){
			updateMedico(m);
		} else if(m.isDelete()){
			m.setCanc("S");
			deleteMedico(m);
		}
	}
	
	public void salvaMedicoDiPersona(MedicoDiPersona m, PersonaCompleta pc){
		if(m.isInsert()){
			Rel_Persona_Medico rpm = new Rel_Persona_Medico();
			Medico medico = m.getMedico();
			medico.setOperation(medico.OP_INSERT);
			saveMedico(medico);
			rpm.setIdmedico(medico.getUnid());
			rpm.setIdpersona(pc.getPersona().getUnid());
			inserisciRel_Persona_Medico(rpm);
//			Rel_Persona_Medico rpm = new Rel_Persona_Medico();
//			rpm.setIdmedico(m.getMedico().getUnid());
//			rpm.setIdpersona(pc.getPersona().getUnid());
//			saveMedico(m.getMedico());
//			inserisciRel_Persona_Medico(rpm);
		}
		if(m.isUpdate()){
//			Rel_Persona_Medico rpm = new Rel_Persona_Medico();
//			saveMedico(m.getMedico());
//			rpm.setIdmedico(m.getMedico().getUnid());
//			rpm.setIdpersona(pc.getPersona().getUnid());
//			updateRel_Persona_Medico(rpm);
		}
	}
	
	/**
	 * Torna ultimo medico scelto che ha data a null
	 * 
	 * @return
	 */
	
	public MedicoDiPersona getMedicoAttivo(PersonaCompleta pc) {
		if (pc.getMedici().isEmpty()) {
			return null;
		}
		for (MedicoDiPersona p : pc.getMedici()) {
			if (p.getRelazione().getDataA() == null)
				return p;
		}
		return null;
	}

	// chiude il medico precedente se esiste
	public void chiudiMedicoAttivo(PersonaCompleta pc) {

		MedicoDiPersona ultimoScelto = getMedicoAttivo(pc);
		if (ultimoScelto != null) {
			ultimoScelto.setOperation(ultimoScelto.OP_UPDATE);
			ultimoScelto.getRelazione().setDataA(new Date());
			ultimoScelto.getRelazione().setOperation(ultimoScelto.OP_UPDATE);
		}

	}

	public MedicoDiPersona scegliMedico(PersonaCompleta pc, Medico m) {
		// chiude il medico precedente se esiste
		chiudiMedicoAttivo(pc);

		// crea nuovo medico di persona con medico scelto
		MedicoDiPersona nuovoScelto = new MedicoDiPersona();
		nuovoScelto.setMedico(m);
		nuovoScelto.setOperation(nuovoScelto.OP_INSERT);

		Rel_Persona_Medico relazione = new Rel_Persona_Medico();
		relazione.setIdpersona((pc.getPersona().getUnid()));
		relazione.setIdmedico(m.getUnid());
		relazione.setDataDa(new Date());
		relazione.setDataA(null);
		relazione.setOperation(relazione.OP_INSERT);
		nuovoScelto.setRelazione(relazione);

		pc.getMedici().add(nuovoScelto);

		return nuovoScelto;
	}

	/************************ ESENZIONE DI PERSONA *************************/
	
	public void loadEsenzioni(Persona p, PersonaCompleta pc) {
		Rel_Persona_EsenzioneSearch rpes = new Rel_Persona_EsenzioneSearch();
		rpes.setIdpersona(Integer.toString(p.getUnid()));
		List<Rel_Persona_Esenzione> rel_Persona_Esenzione = cercaRel_Persona_Esenzione(rpes);

		if (rel_Persona_Esenzione.isEmpty()) {
			new RuntimeException("Non ci sono esenzioni relative alla persona");
		} else {

			for (Rel_Persona_Esenzione rpe : rel_Persona_Esenzione) {
				rpe.setOperation(pc.OP_NOP);
				EsenzioneSearch es = new EsenzioneSearch();
				es.setUnid(Integer.parseInt(rpe.getIdesenzione()));
				List<Esenzione> esenzioni = cercaEsenzioni(es);
				EsenzioneDiPersona edp = new EsenzioneDiPersona();
				edp.setOperation(pc.OP_NOP);
				edp.setEsenzione(esenzioni.get(0));
				edp.setRelazione(rpe);
				pc.getEsenzioni().add(edp);
			}
		}
	}
	
	private void saveEsenzione(Esenzione e){
		if(e.isInsert()){
			insertEsenzione(e);
		} else if(e.isUpdate()){
			updateEsenzione(e);
		} else if(e.isDelete()){
			deleteEsenzione(e);
		}
	}
	
	public void salvaEsenzioneDiPersona(EsenzioneDiPersona edp, PersonaCompleta pc){
		if(edp.isInsert()){
			Rel_Persona_Esenzione rpe = new Rel_Persona_Esenzione();
			Esenzione esenzione = edp.getEsenzione();
			esenzione.setOperation(esenzione.OP_INSERT);
			saveEsenzione(esenzione);
			rpe.setIdesenzione(Integer.toString(esenzione.getUnid()));
			rpe.setIdpersona(Integer.toString(pc.getPersona().getUnid()));
			inserisciRel_Persona_Esenzione(rpe);
		} else if(edp.isUpdate()){
			Esenzione esenzione = edp.getEsenzione();
			esenzione.setOperation(esenzione.OP_UPDATE);
			saveEsenzione(esenzione);
//			Rel_Persona_Esenzione rpe = new Rel_Persona_Esenzione();
//			saveEsenzione(edp.getEsenzione());
//			rpe.setIdesenzione(Integer.toString(edp.getEsenzione().getUnid()));
//			rpe.setIdpersona(Integer.toString(pc.getPersona().getUnid()));
//			updateRel_Persona_Esenzione(rpe);
		} 
	}
	
	public EsenzioneDiPersona getEsenzioneAttiva(PersonaCompleta pc) {
		if (pc.getEsenzioni().isEmpty()) {
			return null;
		}
		for (EsenzioneDiPersona edp : pc.getEsenzioni()) {
			if (edp.getRelazione().getDataA() == null)
				return edp;
		}
		return null;
	}
	
	public void chiudiEsenzioneAttiva(PersonaCompleta pc) {

		EsenzioneDiPersona ultimaEsenzione = getEsenzioneAttiva(pc);
		if (ultimaEsenzione != null) {
			ultimaEsenzione.setOperation(ultimaEsenzione.OP_UPDATE);
			ultimaEsenzione.getRelazione().setDataA(new Date());
			ultimaEsenzione.getRelazione().setOperation(ultimaEsenzione.OP_UPDATE);
		}

	}

	public EsenzioneDiPersona selezionaEsenzione(PersonaCompleta pc, Esenzione esenzione) {
		// chiude il medico precedente se esiste
		chiudiEsenzioneAttiva(pc);

		// crea nuovo medico di persona con medico scelto
		EsenzioneDiPersona nuovaEsenzione = new EsenzioneDiPersona();
		nuovaEsenzione.setEsenzione(esenzione);
		nuovaEsenzione.setOperation(nuovaEsenzione.OP_INSERT);

		Rel_Persona_Esenzione relazione = new Rel_Persona_Esenzione();
		relazione.setIdpersona(Integer.toString((pc.getPersona().getUnid())));
		relazione.setIdesenzione(Integer.toString(esenzione.getUnid()));
		relazione.setDataDa(new Date());
		relazione.setDataA(null);
		relazione.setOperation(relazione.OP_INSERT);
		nuovaEsenzione.setRelazione(relazione);

		pc.getEsenzioni().add(nuovaEsenzione);

		return nuovaEsenzione;
	}

}
