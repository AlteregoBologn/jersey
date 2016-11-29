package tari.model;

import model.E;

public class PersonaGiuridica extends E {

	private String descrizione;
	private String pec;
	private String piva;
	private Integer rappresentante;
	private Integer unid;

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
	public void setRappresentante(Integer a) {

		this.rappresentante = a;
	}
	public Integer getRappresentante() {

		return rappresentante;
	}
	public void setUnid(Integer a) {

		this.unid = a;
	}
	public Integer getUnid() {

		return unid;
	}
}
