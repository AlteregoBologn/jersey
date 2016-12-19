package model;

import java.util.Date;

public class Persona extends E {

	private Integer unid;
	private String nome, cognome, cf, canc;
	private String password;
	private String email;
	private String sesso;
	private Date datanascita;
	private String comunenascita;
	private String pec;
	private String recapitotelefonico;
	private Integer dittaIndividuale;

	public Date getDatanascita() {
		return datanascita;
	}

	public void setDatanascita(Date datanascita) {
		this.datanascita = datanascita;
	}

	public String getComunenascita() {
		return comunenascita;
	}

	public void setComunenascita(String comunenascita) {
		this.comunenascita = comunenascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}


	public Integer getUnid() {
		return unid;
	}

	public void setUnid(Integer unid) {
		this.unid = unid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getCanc() {
		return canc;
	}

	public void setCanc(String canc) {
		this.canc = canc;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPec(String a) {

		this.pec = a;
	}
	public String getPec() {

		return pec;
	}
	public void setRecapitotelefonico(String a) {

		this.recapitotelefonico = a;
	}
	public String getRecapitotelefonico() {

		return recapitotelefonico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDittaIndividuale() {
		return dittaIndividuale;
	}

	public void setDittaIndividuale(Integer dittaIndividuale) {
		this.dittaIndividuale = dittaIndividuale;
	}
	
	

}
