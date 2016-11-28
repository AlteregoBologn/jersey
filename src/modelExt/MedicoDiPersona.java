package modelExt;

import model.E;
import model.Medico;
import model.Rel_Persona_Medico;

public class MedicoDiPersona extends E {
	
	Medico medico;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medico == null) ? 0 : medico.hashCode());
		result = prime * result + ((relazione == null) ? 0 : relazione.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicoDiPersona other = (MedicoDiPersona) obj;
		if (medico == null) {
			if (other.medico != null)
				return false;
		} else if (!medico.equals(other.medico))
			return false;
		if (relazione == null) {
			if (other.relazione != null)
				return false;
		} else if (!relazione.equals(other.relazione))
			return false;
		return true;
	}
}
