package tari.modelExt;

import model.E;
import tari.model.Immobile;
import tari.model.relationModel.Rel_Dichiarazione_Immobile;

public class DichiarazioneDiImmobile extends E {
	
	Immobile immobile;
	
	Rel_Dichiarazione_Immobile rel_Dichiarazione_Immobile;//TODO sono private?


	public Immobile getImmobile() {
		return immobile;
	}

	public void setImmobile(Immobile immobile) {
		this.immobile = immobile;
	}

	public Rel_Dichiarazione_Immobile getRel_Dichiarazione_Immobile() {
		return rel_Dichiarazione_Immobile;
	}

	public void setRel_Dichiarazione_Immobile(Rel_Dichiarazione_Immobile rel_Dichiarazione_Immobile) {
		this.rel_Dichiarazione_Immobile = rel_Dichiarazione_Immobile;
	}

}
