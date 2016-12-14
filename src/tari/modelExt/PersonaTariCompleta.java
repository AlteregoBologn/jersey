package tari.modelExt;

import java.util.ArrayList;
import java.util.List;

import model.E;
import model.Indirizzo;
import tari.model.Allegato;
import tari.model.PersonaGiuridica;
import tari.model.PersonaTari;

public class PersonaTariCompleta extends E {
	PersonaTari personaTari;
	
	PersonaGiuridica personaGiuridica;
	
	Allegato	cartaIdentita=new Allegato();

	Allegato	visuraCamerale=new Allegato();
	
	Indirizzo residenza;
	
	//Indirizzo domicilio;
	List<DichiarazioneDiPersonaTari> dichiarazioniDiPersona = new ArrayList<DichiarazioneDiPersonaTari>();
	
	public PersonaTari getPersonaTari() {
		return personaTari;
	}
	public void setPersonaTari(PersonaTari personaTari) {
		this.personaTari = personaTari;
	}
	public List<DichiarazioneDiPersonaTari> getDichiarazioniDiPersona() {
		return dichiarazioniDiPersona;
	}
	public void setDichiarazioniDiPersona(List<DichiarazioneDiPersonaTari> dichiarazioniDiPersona) {
		this.dichiarazioniDiPersona = dichiarazioniDiPersona;
	}
	public Indirizzo getResidenza() {
		return residenza;
	}
	public void setResidenza(Indirizzo residenza) {
		this.residenza = residenza;
	}
	public PersonaGiuridica getPersonaGiuridica() {
		return personaGiuridica;
	}
	public void setPersonaGiuridica(PersonaGiuridica personaGiuridica) {
		this.personaGiuridica = personaGiuridica;
	}
	public Allegato getCartaIdentita() {
		return cartaIdentita;
	}
	public void setCartaIdentita(Allegato cartaIdentita) {
		this.cartaIdentita = cartaIdentita;
	}
	public Allegato getVisuraCamerale() {
		return visuraCamerale;
	}
	public void setVisuraCamerale(Allegato visuraCamerale) {
		this.visuraCamerale = visuraCamerale;
	}
	
	
}
