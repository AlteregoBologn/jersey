package model;

public class Decodifica extends E {

	private String canc;
	private String descrizione;
	private String fkDecodificaUnid;
	private String tipo;
	private Integer unid;

	public Decodifica() {
		
	}
	public Decodifica(Integer un) {
		unid=un;
	}
	
	public void setCanc(String a) {

		this.canc = a;
	}
	public String getCanc() {

		return canc;
	}
	public void setDescrizione(String a) {

		this.descrizione = a;
	}
	public String getDescrizione() {

		return descrizione;
	}
	public void setFkDecodificaUnid(String a) {

		this.fkDecodificaUnid = a;
	}
	public String getFkDecodificaUnid() {

		return fkDecodificaUnid;
	}
	public void setTipo(String a) {

		this.tipo = a;
	}
	public String getTipo() {

		return tipo;
	}
	public void setUnid(Integer a) {

		this.unid = a;
	}
	public Integer getUnid() {

		return unid;
	}
	/*
	public String getDescrizionePerCombo(){
		return unid+" - "+descrizione;
	}*/
	
}
