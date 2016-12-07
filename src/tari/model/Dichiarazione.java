package tari.model;

import java.util.Date;

import model.Decodifica;
import model.E;

public class Dichiarazione extends E {

	private Integer unid;
	private Date data;
	private String firma;
	private Integer italianoallestero;
	private Integer unicooccupante;
	private Integer agricoltore;	
	private Decodifica decodifica;

	public void setAgricoltore(Integer a) {

		this.agricoltore = a;
	}

	public Integer getAgricoltore() {

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

	public void setItalianoallestero(Integer a) {

		this.italianoallestero = a;
	}

	public Integer getItalianoallestero() {

		return italianoallestero;
	}

	public void setUnicooccupante(Integer a) {

		this.unicooccupante = a;
	}

	public Integer getUnicooccupante() {

		return unicooccupante;
	}

	public void setUnid(Integer a) {

		this.unid = a;
	}

	public Integer getUnid() {

		return unid;
	}

	public Decodifica getDecodifica() {
		return decodifica;
	}

	public void setDecodifica(Decodifica decodifica) {
		this.decodifica = decodifica;
	}
}