package tari.modelExt;

import model.E;
import tari.model.Dichiarazione;
import tari.model.PrecedenteDichiarazione;
import tari.model.relationModel.Rel_Dichiarazione_PrecDichiara;
import tari.model.relationModel.Rel_PersGiur_Dichiarazione;
import tari.model.relationModel.Rel_PersonaTari_Dichiarazione;

public class DichiarazioneDiPersonaTari extends E {
	
	Dichiarazione dichiarazione;
	
	PrecedenteDichiarazione precedenteDichiarazione;
	
	ImmobileDiDichiarazione dichiarazioneImmobile;
	
	Rel_Dichiarazione_PrecDichiara rel_Dichiarazione_PrecDichiara;
	
	Rel_PersonaTari_Dichiarazione rel_Persona_Dichiarazione;
	
	//Rel_PersGiur_Dichiarazione rel_PersGiur_Dichiarazione;
	

	public Dichiarazione getDichiarazione() {
		return dichiarazione;
	}

	public void setDichiarazione(Dichiarazione dichiarazione) {
		this.dichiarazione = dichiarazione;
	}

	public Rel_PersonaTari_Dichiarazione getRel_Persona_Dichiarazione() {
		return rel_Persona_Dichiarazione;
	}

	public void setRel_Persona_Dichiarazione(Rel_PersonaTari_Dichiarazione rel_Persona_Dichiarazione) {
		this.rel_Persona_Dichiarazione = rel_Persona_Dichiarazione;
	}

	public Rel_Dichiarazione_PrecDichiara getRel_Dichiarazione_PrecDichiara() {
		return rel_Dichiarazione_PrecDichiara;
	}

	public void setRel_Dichiarazione_PrecDichiara(Rel_Dichiarazione_PrecDichiara rel_Dichiarazione_PrecDichiara) {
		this.rel_Dichiarazione_PrecDichiara = rel_Dichiarazione_PrecDichiara;
	}

	public PrecedenteDichiarazione getPrecedenteDichiarazione() {
		return precedenteDichiarazione;
	}

	public void setPrecedenteDichiarazione(PrecedenteDichiarazione precedenteDichiarazione) {
		this.precedenteDichiarazione = precedenteDichiarazione;
	}

	public ImmobileDiDichiarazione getDichiarazioneImmobile() {
		return dichiarazioneImmobile;
	}
	public void setDichiarazioneImmobile(ImmobileDiDichiarazione dichiarazioneImmobili) {
		this.dichiarazioneImmobile = dichiarazioneImmobili;
	}

	@Override
	public String toString() {
		return "DichiarazioneDiPersonaTari [dichiarazione=" + dichiarazione + ", precedenteDichiarazione="
				+ precedenteDichiarazione + ", dichiarazioneImmobile=" + dichiarazioneImmobile
				+ ", rel_Dichiarazione_PrecDichiara=" + rel_Dichiarazione_PrecDichiara + ", rel_Persona_Dichiarazione="
				+ rel_Persona_Dichiarazione + ", getDichiarazione()=" + getDichiarazione()
				+ ", getRel_Persona_Dichiarazione()=" + getRel_Persona_Dichiarazione()
				+ ", getRel_Dichiarazione_PrecDichiara()=" + getRel_Dichiarazione_PrecDichiara()
				+ ", getPrecedenteDichiarazione()=" + getPrecedenteDichiarazione() + ", getDichiarazioneImmobile()="
				+ getDichiarazioneImmobile() + "]";
	}

	

	
	
	
}
