package logic;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

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
			ret.add(pc);

			Rel_Persona_MedicoSearch rpms = new Rel_Persona_MedicoSearch();
			rpms.setIdpersona(p.getUnid());
			List<Rel_Persona_Medico> rel_Persona_Medico = cercaRel_Persona_Medico(rpms);

			for (Rel_Persona_Medico rpm : rel_Persona_Medico) {
				rpm.setOperation(pc.OP_UPDATE);
				MedicoSearch ms = new MedicoSearch();
				ms.setUnid(rpm.getIdmedico());
				List<Medico> medici = cercaMedico(ms);
				//
				MedicoDiPersona m = new MedicoDiPersona();
				m.setOperation(pc.OP_UPDATE);
				m.setMedico(medici.get(0));
				m.setRelazione(rpm);
				pc.getMedici().add(m);
			}
			Rel_Persona_EsenzioneSearch rpes = new Rel_Persona_EsenzioneSearch();
			rpes.setIdpersona(Integer.toString(p.getUnid()));
			List<Rel_Persona_Esenzione> rel_Persona_Esenzione = cercaRel_Persona_Esenzione(rpes);
			
			for (Rel_Persona_Esenzione rpe : rel_Persona_Esenzione)
			{
				rpe.setOperation(pc.OP_UPDATE);
				EsenzioneSearch es = new EsenzioneSearch();
				es.setUnid(Integer.parseInt(rpes.getIdesenzione()));
				List<Esenzione> esenzioni = cercaEsenzioni(es);
				
				EsenzioneDiPersona edp = new EsenzioneDiPersona();
				edp.setOperation(pc.OP_UPDATE);
				edp.setEsenzione(esenzioni.get(0));
				edp.setRelazione(rpe);
				pc.getEsenzioni().add(edp);
			}
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

	public void savePersonaCompleta(PersonaCompleta p) {
		if (p.isInsert() || p.isUpdate()) {
			savePersona(p.getPersona());
			for (MedicoDiPersona m : p.getMedici()) {
				saveMedicoDiPersona(m);
			}
		} else if (p.isDelete()) {
			savePersona(p.getPersona());
			for (MedicoDiPersona m : p.getMedici()) {
				saveMedicoDiPersona(m);
			}
		}
		p.setOperation(p.OP_NOP);
	}
	
	/************************ MEDICO DI PERSONA *************************/
	
	
	private void saveMedico(Medico m) {
		if (m.isInsert()) {
			insertMedico(m);
		} else if (m.isUpdate()) {
			updateMedico(m);
		} else if (m.isDelete()) {
			m.setCanc("S");
			updateMedico(m);
		}
	}
	
	public void saveMedicoDiPersona(MedicoDiPersona mp) {
		if (mp.isInsert()) {
			Rel_Persona_Medico rpm = new Rel_Persona_Medico();
			rpm.setIdmedico(mp.getMedico().getUnid());
			rpm.setDataA(null);
			rpm.setDataDa(null);
			inserisciRel_Persona_Medico(rpm);
		} else if (mp.isUpdate()) {

		} else if (mp.isDelete()) {

		} else if (mp.isNop()) {

		}
		

	}
}
