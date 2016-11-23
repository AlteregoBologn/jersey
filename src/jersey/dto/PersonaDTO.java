package jersey.dto;

import java.util.ArrayList;
import java.util.List;

import model.Esenzione;
import model.Medico;

public class PersonaDTO  {
	String nome;
	String cognome;
	String codiceFiscale;
	String indirizzo;
	
	
	List<Esenzione> esenzioni = new ArrayList<Esenzione>();
	List<Medico> medici = new ArrayList<Medico>();

	
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public List<Esenzione> getEsenzioni() {
		return esenzioni;
	}

	public void setEsenzioni(List<Esenzione> esenzioni) {
		this.esenzioni = esenzioni;
	}

	public List<Medico> getMedici() {
		return medici;
	}

	public void setMedici(List<Medico> medici) {
		this.medici = medici;
	}

	@Override
	public String toString() {
		return "PersonaDTO [persona=" + codiceFiscale + ", esenzioni=" + esenzioni + ", medici=" + medici + "]";
	}

}
