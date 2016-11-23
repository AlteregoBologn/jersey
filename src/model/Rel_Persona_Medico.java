package model;

public class Rel_Persona_Medico {
	 
	  private String dataA;
	  private String dataDa;
	  private Integer idmedico;
	  private Integer idpersona;
	 
	  public void setDataA(String a) {
	 
	    this.dataA = a;
	  }
	  public String getDataA() {
	 
	    return dataA;
	  }
	  public void setDataDa(String a) {
	 
	    this.dataDa = a;
	  }
	  public String getDataDa() {
	 
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

	}
