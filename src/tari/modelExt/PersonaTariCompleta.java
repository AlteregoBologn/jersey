package tari.modelExt;

import java.util.ArrayList;
import java.util.List;

import model.E;
import tari.model.PersonaGiuridica;
import tari.model.PersonaTari;

public class PersonaTariCompleta extends E {
	PersonaTari personaTari;
	
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
	@Override
	public String toString() {
		return "PersonaTariCompleta [personaTari=" + personaTari + ", dichiarazioniDiPersona=" + dichiarazioniDiPersona
				+ "]";
	}

}
