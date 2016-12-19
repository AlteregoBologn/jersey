package tari.model;

import model.E;

public class PersonaGiuridica extends E {

	private Integer unid;
	private String descrizione;
	private String sedeLegale;
	private String provincia;
	private String indirizzo;
	private Integer numeroCivico;
	private String pIva;
	private String recapitoTelefonico;
	private String email;
	private String pec;
	
	public Integer getUnid() {
		return unid;
	}

	public void setUnid(Integer unid) {
		this.unid = unid;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getSedeLegale() {
		return sedeLegale;
	}

	public void setSedeLegale(String sedeLegale) {
		this.sedeLegale = sedeLegale;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Integer getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(Integer numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public String getpIva() {
		return pIva;
	}

	public void setpIva(String pIva) {
		this.pIva = pIva;
	}

	public String getRecapitoTelefonico() {
		return recapitoTelefonico;
	}

	public void setRecapitoTelefonico(String recapitoTelefonico) {
		this.recapitoTelefonico = recapitoTelefonico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}


	@Override
	public String toString() {
		return "PersonaGiuridica [unid=" + unid + ", descrizione=" + descrizione + ", sedeLegale=" + sedeLegale
				+ ", provincia=" + provincia + ", indirizzo=" + indirizzo + ", numeroCivico=" + numeroCivico + ", pIva="
				+ pIva + ", recapitoTelefonico=" + recapitoTelefonico + ", email=" + email + ", pec=" + pec + "]";
	}
	
	
	
}
