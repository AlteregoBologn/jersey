package tari.modelExt;

import java.util.ArrayList;
import java.util.List;

import model.E;
import tari.model.Immobile;
import tari.model.relationModel.Rel_Dichiarazione_Immobile;

public class ImmobileDiDichiarazione extends E {
	
	Immobile immobile;
	
	Rel_Dichiarazione_Immobile rel_Dichiarazione_Immobile;//TODO sono private?

	List<LocaleDiImmobile> localiDiImmobile = new ArrayList<LocaleDiImmobile>();

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

	public List<LocaleDiImmobile> getLocaliDiImmobile() {
		return localiDiImmobile;
	}

	public void setLocaliDiImmobile(List<LocaleDiImmobile> localiDiImmobile) {
		this.localiDiImmobile = localiDiImmobile;
	}

	@Override
	public String toString() {
		return "ImmobileDiDichiarazione [immobile=" + immobile + ", rel_Dichiarazione_Immobile="
				+ rel_Dichiarazione_Immobile + ", localiDiImmobile=" + localiDiImmobile + "]";
	}

	
	
}
