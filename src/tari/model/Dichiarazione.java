package tari.model;

import model.E;

public class Dichiarazione extends E {

	private String agricoltore;
	private String data;//TODO trasformare in Date
	private String firma;
	private String italianoallestero;
	private String unicooccupante;
	private int unid;

	public void setAgricoltore(String a) {

		this.agricoltore = a;
	}
	public String getAgricoltore() {

		return agricoltore;
	}
	public void setData(String a) {

		this.data = a;
	}
	public String getData() {

		return data;
	}
	public void setFirma(String a) {

		this.firma = a;
	}
	public String getFirma() {

		return firma;
	}
	public void setItalianoallestero(String a) {

		this.italianoallestero = a;
	}
	public String getItalianoallestero() {

		return italianoallestero;
	}
	public void setUnicooccupante(String a) {

		this.unicooccupante = a;
	}
	public String getUnicooccupante() {

		return unicooccupante;
	}
	public void setUnid(int a) {

		this.unid = a;
	}
	public int getUnid() {

		return unid;
	}
}