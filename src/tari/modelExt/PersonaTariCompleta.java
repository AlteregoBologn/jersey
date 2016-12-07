package tari.modelExt;

import java.util.ArrayList;
import java.util.List;

import model.E;
import model.Indirizzo;
import tari.model.PersonaTari;

public class PersonaTariCompleta extends E {
	PersonaTari personaTari;
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
}
