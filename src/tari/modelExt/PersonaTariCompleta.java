package tari.modelExt;

import java.util.ArrayList;
import java.util.List;

import model.E;
import model.Indirizzo;
import model.Rel_Persona_Indirizzo;
import tari.model.Allegato;
import tari.model.PersonaGiuridica;
import tari.model.PersonaTari;
import tari.model.relationModel.Rel_PersonaTari_Allegato;
import tari.model.relationModel.Rel_PersonaTari_PersonaGiuridica;

public class PersonaTariCompleta extends E {
	PersonaTari personaTari;
	
	PersonaGiuridica personaGiuridica=new PersonaGiuridica();
	Rel_PersonaTari_PersonaGiuridica rel_PersonaTari_PersonaGiuridica=new Rel_PersonaTari_PersonaGiuridica();
	
	Allegato	cartaIdentita;
	Rel_PersonaTari_Allegato relCartaIdentita;
	
	Allegato	visuraCamerale;
	Rel_PersonaTari_Allegato relVisuraCamerale;
	
	Indirizzo residenza;
	Rel_Persona_Indirizzo rel_Persona_Indirizzo;
	
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
