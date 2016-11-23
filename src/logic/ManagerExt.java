package logic;

import java.util.ArrayList;
import java.util.List;

import model.Medico;
import model.MedicoSearch;
import model.Persona;
import model.PersonaSearch;
import model.Rel_Persona_Medico;
import model.Rel_Persona_MedicoSearch;
import modelExt.MedicoDiPersona;
import modelExt.PersonaCompleta;

public class ManagerExt extends Manager {
	
	List<PersonaCompleta> loadPersonaCompleta(PersonaSearch ps) {
		List<PersonaCompleta> ret=new ArrayList();
		
		List<Persona> persone = cercaPersone(ps);
		for(Persona p:persone){
			PersonaCompleta n=new PersonaCompleta();
			n.setOperation(n.OP_UPDATE);
			n.setPersona(p);
			
			Rel_Persona_MedicoSearch s=new Rel_Persona_MedicoSearch();
			s.setIdpersona(p.getUnid());			
			List<Rel_Persona_Medico> rel_Persona_Medico = cercaRel_Persona_Medico(s);
			
			for(Rel_Persona_Medico r:rel_Persona_Medico){
				r.setOperation(n.OP_UPDATE);
				MedicoSearch ms=new MedicoSearch();
				ms.setUnid(r.getIdmedico());
				List<Medico> medici = cercaMedico(ms);
				//
				MedicoDiPersona m=new MedicoDiPersona();
				m.setOperation(n.OP_UPDATE);
				m.setMedico(medici.get(0));
				m.setRelazione(r);
			}
			
			
			
			//n.setEsenzioni(esenzioni);
		}
		
		
		return ret;
		
	}
	
	private void salvaMedicoDiPersona(MedicoDiPersona mp) {
		if(mp.isInsert()){
			
		} else if(mp.isUpdate()){
			
		} else if(mp.isDelete()){
		
		} else if(mp.isNop()){
			
		}
		/*
		Rel_Persona_Medico rn=new Rel_Persona_Medico();
		rn.setIdmedico(m.getUnid());
		rn.setIdpersona(p.getPersona().getUnid());
		rn.setDataDa(a);
		rn.setDataA(a);
		inserisciRel_Persona_Medico(rn);
		*/
		
	}
	
	private void savePersona(Persona p) {
		if(p.isInsert()){			
			insertPersona(p);
		} else if(p.isUpdate()){
			updatePersona(p);
		} else if(p.isDelete()){
			p.setCanc("S");
			updatePersona(p);
		}
		
	}
	public void savePersonaCompleta(PersonaCompleta p) {
		if(p.isInsert() || p.isUpdate()){			
			savePersona(p.getPersona());
			for(MedicoDiPersona m:p.getMedici()){
				salvaMedicoDiPersona(m);				
			}
		} else if(p.isDelete()){
			savePersona(p.getPersona());
			for(MedicoDiPersona m:p.getMedici()){
				salvaMedicoDiPersona(m);				
			}
		}
		p.setOperation(p.OP_NOP);
	}
}
