package tari.model;

import java.util.Date;

import model.E;

public class PrecedenteDichiarazione extends E {

	private Integer unid;
	private String civico;
	private Date datada;
	private String interno;
	private String motivo;
	private String via;
	private String comunicazione;
	

	public void setUnid(Integer a) {

		this.unid = a;
	}

	public Integer getUnid() {

		return unid;
	}

	public void setCivico(String a) {

		this.civico = a;
	}

	public String getCivico() {

		return civico;
	}

	public void setDataDa(Date a) {

		this.datada = a;
	}

	public Date getDataDa() {

		return datada;
	}

	public void setInterno(String a) {

		this.interno = a;
	}

	public String getInterno() {

		return interno;
	}

	public void setMotivo(String a) {

		this.motivo = a;
	}

	public String getMotivo() {

		return motivo;
	}

	
	public void setVia(String a) {

		this.via = a;
	}

	public String getVia() {

		return via;
	}

	public String getComunicazione() {
		return comunicazione;
	}

	public void setComunicazione(String comunicazione) {
		this.comunicazione = comunicazione;
	}

	@Override
	public String toString() {
		return "PrecedenteDichiarazione [unid=" + unid + ", civico=" + civico + ", datada=" + datada + ", interno="
				+ interno + ", motivo=" + motivo + ", via=" + via + ", comunicazione=" + comunicazione + "]";
	}
	
	
	
}
