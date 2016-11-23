package web.comp.registrazione;

import model.Persona;
import web.BasePage;

public class RegistrazionePage extends BasePage {
	
	
	public RegistrazionePage(){
		Persona p = new Persona();
		add(new WizardRegistrazionePanel("regPanel", p));
		//add(new WizardPanel("regPanel", p));	
	}
	
}
