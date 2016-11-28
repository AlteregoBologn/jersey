package tari.model;

import model.E;

public class Immobile extends E {

	private String civico;
	private String datada;
	private String interno;
	private String nomeprecedentedetentore;
	private String nomeproprietario;
	private String piano;
	private String qualita;
	private String via;
	private int unid;

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
	public void setNomeprecedentedetentore(String a) {

		this.nomeprecedentedetentore = a;
	}
	public String getNomeprecedentedetentore() {

		return nomeprecedentedetentore;
	}
	public void setNomeproprietario(String a) {

		this.nomeproprietario = a;
	}
	public String getNomeproprietario() {

		return nomeproprietario;
	}
	public void setPiano(String a) {

		this.piano = a;
	}
	public String getPiano() {

		return piano;
	}
	public void setQualita(String a) {

		this.qualita = a;
	}
	public String getQualita() {

		return qualita;
	}
	public void setVia(String a) {

		this.via = a;
	}
	public String getVia() {

		return via;
	}
	public void setUnid(int a) {

		this.unid = a;
	}
	public int getUnid() {

		return unid;
	}
}
