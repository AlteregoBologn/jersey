package model;

import java.util.Date;

public class Rel_Persona_Esenzione extends E {

	private Date dataA;
	private Date dataDa;
	private String idesenzione;
	private String idpersona;

	public void setDataA(Date a) {

		this.dataA = a;
	}
	public Date getDataA() {

		return dataA;
	}
	public void setDataDa(Date a) {

		this.dataDa = a;
	}
	public Date getDataDa() {

		return dataDa;
	}
	public void setIdesenzione(String a) {

		this.idesenzione = a;
	}
	public String getIdesenzione() {

		return idesenzione;
	}
	public void setIdpersona(String a) {

		this.idpersona = a;
	}
	public String getIdpersona() {

		return idpersona;
	}
	@Override
	public String toString() {
		return "Rel_Persona_Esenzione [dataA=" + dataA + ", dataDa=" + dataDa + ", idesenzione=" + idesenzione
				+ ", idpersona=" + idpersona + "]";
	}
	
	
}
