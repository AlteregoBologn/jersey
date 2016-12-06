package model;

public class Codifica {
	
	private Integer unid;
	private String descrizione;
	private String tipo;
	
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Codifica [unid=" + unid + ", descrizione=" + descrizione + ", tipo=" + tipo + "]";
	}
	
	
	
	

}
