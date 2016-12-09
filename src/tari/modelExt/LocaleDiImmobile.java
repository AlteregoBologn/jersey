package tari.modelExt;

import model.Decodifica;
import model.E;
import tari.model.Locale;
import tari.model.relationModel.Rel_Immobile_Locale;

public class LocaleDiImmobile extends E {
	
	Locale	locale;
	
	Rel_Immobile_Locale rel_Immobile_Locale;
	
	Decodifica decodifica = new Decodifica();

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Rel_Immobile_Locale getRel_Immobile_Locale() {
		return rel_Immobile_Locale;
	}

	public void setRel_Immobile_Locale(Rel_Immobile_Locale rel_Immobile_Locale) {
		this.rel_Immobile_Locale = rel_Immobile_Locale;
	}

	public Decodifica getDecodifica() {
		return decodifica;
	}

	public void setDecodifica(Decodifica decodifica) {
		this.decodifica = decodifica;
	}

}
