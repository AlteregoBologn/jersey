package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Medico extends E  {
	
	private Integer unid;
	private String nome, cognome, cf, canc;
	private String password;
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
