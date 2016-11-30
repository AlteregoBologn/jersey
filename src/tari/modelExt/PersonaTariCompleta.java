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
		return "PersonaTariCompleta [personaTari=" + personaTari.getNome() + " " + personaTari.getCognome() + 
				", dichiarazioniDiPersona=" + dichiarazioniDiPersona.get(0).getDichiarazione() +
				", DichiarazioniDiImmobli= "+ dichiarazioniDiPersona.get(0).getDichiarazioniImmobili().get(0).getImmobile().getVia() + " "
				 + dichiarazioniDiPersona.get(0).getDichiarazioniImmobili().get(0).getImmobile().getCivico() + 
				", Locali= " + dichiarazioniDiPersona.get(0).getDichiarazioniImmobili().get(0).getLocaliDiImmobile().get(0).getLocale().getTipo()	
				+"Precedenti dichiarazioni= "+ dichiarazioniDiPersona.get(3).getPrecedenteDichiarazione().getVia() + " " +
				 dichiarazioniDiPersona.get(3).getPrecedenteDichiarazione().getMotivo() + "]";
	}


}
