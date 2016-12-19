package tari.model;

import model.E;

public class Allegato extends E {
	
	private Integer unid;
	private String 	tipo;
	private byte[]	data;
	

	public Integer getUnid() {
		return unid;
	}
	public void setUnid(Integer unid) {
		this.unid = unid;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
	
	
}
