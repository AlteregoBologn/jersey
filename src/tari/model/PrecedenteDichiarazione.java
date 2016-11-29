package tari.model;

import java.sql.Date;
import java.time.LocalDate;

import model.E;

public class PrecedenteDichiarazione extends E {

	private String civico;
	private String datada;// TODO rivedere tipo per interfaccia
	private String interno;
	private String motivo;
	private Integer unid;
	private String via;

	public void setCivico(String a) {

		this.civico = a;
	}
	public String getCivico() {

		return civico;
	}
	public void setDatada(String a) {

		this.datada = a;
	}
	public String getDatada() {

		return datada;
	}
	public void setInterno(String a) {

		this.interno = a;
	}
	public String getInterno() {

		return interno;
	}
	public void setMotivo(String a) {

		this.motivo = a;
	}
	public String getMotivo() {

		return motivo;
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
