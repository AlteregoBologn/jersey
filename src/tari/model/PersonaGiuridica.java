package tari.model;

import model.E;

public class PersonaGiuridica extends E {

	private String descrizione;
	private String pec;
	private String piva;
	private int rappresentante;
	private int unid;

	public void setDescrizione(String a) {

		this.descrizione = a;
	}
	public String getDescrizione() {

		return descrizione;
	}
	public void setPec(String a) {

		this.pec = a;
	}
	public String getPec() {

		return pec;
	}
	public void setPiva(String a) {

		this.piva = a;
	}
	public String getPiva() {

		return piva;
	}
	public void setRappresentante(int a) {

		this.rappresentante = a;
	}
	public int getRappresentante() {

		return rappresentante;
	}
	public void setUnid(int a) {

		this.unid = a;
	}
	public int getUnid() {

		return unid;
	}
}
