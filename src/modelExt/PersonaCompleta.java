package modelExt;

import java.util.ArrayList;
import java.util.List;

import model.E;
import model.Esenzione;
import model.Persona;

public class PersonaCompleta extends E {
	Persona persona;
	
	List<Esenzione>	esenzioni=new ArrayList();
	List<MedicoDiPersona> medici=new ArrayList();
	
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Esenzione> getEsenzioni() {
		return esenzioni;
	}

	public void setEsenzioni(List<Esenzione> esenzioni) {
		this.esenzioni = esenzioni;
	}

	public List<MedicoDiPersona> getMedici() {
		return medici;
	}

	public void setMedici(List<MedicoDiPersona> medici) {
		this.medici = medici;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((esenzioni == null) ? 0 : esenzioni.hashCode());
		result = prime * result + ((medici == null) ? 0 : medici.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
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
		PersonaCompleta other = (PersonaCompleta) obj;
		if (esenzioni == null) {
			if (other.esenzioni != null)
				return false;
		} else if (!esenzioni.equals(other.esenzioni))
			return false;
		if (medici == null) {
			if (other.medici != null)
				return false;
		} else if (!medici.equals(other.medici))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		return true;
	}
	
}
