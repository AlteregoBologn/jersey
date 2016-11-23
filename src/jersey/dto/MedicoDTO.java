package jersey.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import model.Ambulatorio;
import model.Medico;
import model.Visita;

@XmlRootElement
public class MedicoDTO {

	String nome;
	String cognome;
	String codiceFiscale;
	String canc;
	
	List<Ambulatorio> ambulatori = new ArrayList<Ambulatorio>();
	List<Visita> visite = new ArrayList<Visita>();
	List<Medico> pazienti = new ArrayList<Medico>();
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public List<Medico> getPazienti() {
		return pazienti;
	}

	public void setPazienti(List<Medico> pazienti) {
		this.pazienti = pazienti;
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

	public String getCanc() {
		return canc;
	}

	public void setCanc(String canc) {
		this.canc = canc;
	}
	
	public List<Ambulatorio> getAmbulatori() {
		return ambulatori;
	}

	public void setAmbulatori(List<Ambulatorio> ambulatori) {
		this.ambulatori = ambulatori;
	}

	public List<Visita> getVisite() {
		return visite;
	}

	public void setVisite(List<Visita> visite) {
		this.visite = visite;
	}
	
	@Override
	public String toString() {
		return "MedicoDTO [medico=" + codiceFiscale + ", pazienti=" + pazienti + ", ambulatori=" + ambulatori + ", visite = " + visite + "]";
	}
}
