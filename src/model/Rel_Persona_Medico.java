package model;

import java.util.Date;

public class Rel_Persona_Medico extends E {

	private Date dataA;
	private Date dataDa;
	private Integer idmedico;
	private Integer idpersona;

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

	public Integer getIdmedico() {
		return idmedico;
	}

	public void setIdmedico(Integer idmedico) {
		this.idmedico = idmedico;
	}

	public Integer getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(Integer idpersona) {
		this.idpersona = idpersona;
	}
	
	@Override
	public String toString() {
		return "Rel_Persona_Medico [dataA=" + dataA + ", dataDa=" + dataDa + ", idmedico=" + idmedico + ", idpersona=" + idpersona + "]";
	}

}
