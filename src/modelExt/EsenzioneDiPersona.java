package modelExt;

import model.E;
import model.Esenzione;
import model.Rel_Persona_Esenzione;

public class EsenzioneDiPersona extends E {
	Esenzione esenzione=new Esenzione();
	Rel_Persona_Esenzione relazione=new Rel_Persona_Esenzione();

	public Esenzione getEsenzione() {
		return esenzione;
	}

	public void setEsenzione(Esenzione esenzione) {
		this.esenzione = esenzione;
	}

	public Rel_Persona_Esenzione getRelazione() {
		return relazione;
	}

	public void setRelazione(Rel_Persona_Esenzione relazione) {
		this.relazione = relazione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((esenzione == null) ? 0 : esenzione.hashCode());
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
		EsenzioneDiPersona other = (EsenzioneDiPersona) obj;
		if (esenzione == null) {
			if (other.esenzione != null)
				return false;
		} else if (!esenzione.equals(other.esenzione))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EsenzioneDiPersona [esenzione=" + esenzione + ", relazione=" + relazione + "]";
	}

}
