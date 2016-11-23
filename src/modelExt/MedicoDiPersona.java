package modelExt;

import model.E;
import model.Medico;
import model.Rel_Persona_Medico;

public class MedicoDiPersona extends E {
	Medico	medico;
	Rel_Persona_Medico relazione;
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Rel_Persona_Medico getRelazione() {
		return relazione;
	}
	public void setRelazione(Rel_Persona_Medico relazione) {
		this.relazione = relazione;
	}
	
}
