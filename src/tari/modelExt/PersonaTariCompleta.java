package tari.modelExt;

import java.util.List;

import model.E;
import tari.model.PersonaTari;

public class PersonaTariCompleta extends E {
	
	PersonaTari personaTari;
	
	List<DichiarazioneDiPersonaTari> dichiarazioni;
	
	List<DichiarazioneDiImmobile> dichiarazioniImmobili;
		
	public PersonaTari getPersonaTari() {
		return personaTari;
	}
	public void setPersonaTari(PersonaTari personaTari) {
		this.personaTari = personaTari;
	}
	public List<DichiarazioneDiPersonaTari> getDichiarazioni() {
		return dichiarazioni;
	}
	public void setDichiarazioni(List<DichiarazioneDiPersonaTari> dichiarazioni) {
		this.dichiarazioni = dichiarazioni;
	}
	public List<DichiarazioneDiImmobile> getDichiarazioniImmobili() {
		return dichiarazioniImmobili;
	}
	public void setDichiarazioniImmobili(List<DichiarazioneDiImmobile> dichiarazioniImmobili) {
		this.dichiarazioniImmobili = dichiarazioniImmobili;
	}
	@Override
	public String toString() {
		return "PersonaTariCompleta [NomepersonaTari=" + personaTari.getNome() + ", "
				+ "CognomepersonaTari=" + personaTari.getCognome() + ", dichiarazioni=" + dichiarazioni
				+ ", dichiarazioniImmobili=" + dichiarazioniImmobili + "]";
	}

}
