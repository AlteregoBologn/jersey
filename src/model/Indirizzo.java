package model;

public class Indirizzo extends E {

	private String cap;
	private String civico;
	private String comune;
	private Integer unid;
	private String via;

	public void setCap(String a) {

		this.cap = a;
	}
	public String getCap() {

		return cap;
	}
	public void setCivico(String a) {

		this.civico = a;
	}
	public String getCivico() {

		return civico;
	}
	public void setComune(String a) {

		this.comune = a;
	}
	public String getComune() {

		return comune;
	}
	public void setUnid(Integer a) {

		this.unid = a;
	}
	public Integer getUnid() {

		return unid;
	}
	public void setVia(String a) {

		this.via = a;
	}
	public String getVia() {

		return via;
	}
}
