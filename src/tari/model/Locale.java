package tari.model;

import model.E;

public class Locale extends E {

	private String foglio;
	private double mq;
	private String particella;
	private String subalterno;
	private String tipo;
	private Integer unid;

	public void setFoglio(String a) {

		this.foglio = a;
	}
	public String getFoglio() {

		return foglio;
	}
	public void setMq(double a) {

		this.mq = a;
	}
	public double getMq() {

		return mq;
	}
	public void setParticella(String a) {

		this.particella = a;
	}
	public String getParticella() {

		return particella;
	}
	public void setSubalterno(String a) {

		this.subalterno = a;
	}
	public String getSubalterno() {

		return subalterno;
	}
	public void setTipo(String a) {

		this.tipo = a;
	}
	public String getTipo() {

		return tipo;
	}
	public Integer getUnid() {
		return unid;
	}
	public void setUnid(Integer unid) {
		this.unid = unid;
	}
}
