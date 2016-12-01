package tari.model;

import java.util.Date;

import model.E;

public class Dichiarazione extends E {

	private String agricoltore;
	private Date data;// TODO trasformare in Date
	private String firma;
	private String italianoallestero;
	private String unicooccupante;
	private Integer unid;

	public void setAgricoltore(String a) {

		this.agricoltore = a;
	}

	public String getAgricoltore() {

		return agricoltore;
	}

	public void setData(Date a) {

		this.data = a;
	}

	public Date getData() {

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

	public void setUnid(Integer a) {

		this.unid = a;
	}

	public Integer getUnid() {

		return unid;
	}

	@Override
	public String toString() {
		return "Dichiarazione [agricoltore=" + agricoltore + ", data=" + data + ", firma=" + firma
				+ ", italianoallestero=" + italianoallestero + ", unicooccupante=" + unicooccupante + ", unid=" + unid
				+ "]";
	}
}