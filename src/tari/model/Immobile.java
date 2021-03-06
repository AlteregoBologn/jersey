package tari.model;

import java.util.Date;
import java.util.List;

import model.E;

public class Immobile extends E {

	private Integer unid;
	private String civico;
	private Date dataDa;
	private String interno;
	private String nomePrecedenteDetentore;
	private String nomeProprietario;
	private String piano;
	private Integer qualita;
	private String via;
	

	public void setUnid(Integer a) {

		this.unid = a;
	}
	public Integer getUnid() {

		return unid;
	}
	
	public void setCivico(String a) {

		this.civico = a;
	}
	public String getCivico() {

		return civico;
	}
	public void setDatada(Date a) {

		this.dataDa = a;
	}
	public Date getDatada() {

		return dataDa;
	}
	public void setInterno(String a) {

		this.interno = a;
	}
	public String getInterno() {

		return interno;
	}
	public void setNomeprecedentedetentore(String a) {

		this.nomePrecedenteDetentore = a;
	}
	public String getNomeprecedentedetentore() {

		return nomePrecedenteDetentore;
	}
	public void setNomeproprietario(String a) {

		this.nomeProprietario = a;
	}
	public String getNomeproprietario() {

		return nomeProprietario;
	}
	public void setPiano(String a) {

		this.piano = a;
	}
	public String getPiano() {

		return piano;
	}
	public void setQualita(Integer a) {

		this.qualita = a;
	}
	public Integer getQualita() {

		return qualita;
	}
	public void setVia(String a) {

		this.via = a;
	}
	public String getVia() {

		return via;
	}
	
}
