package web.comp.persone;

import web.BasePage;
import web.comp.esenzioni.EsenzioniListPanel;
import web.comp.medico.WizardScegliMedicoPanel;
import web.comp.persone.WizardModificaPersonaPanel;

public class ModificaPersonaPage extends BasePage {
	
	public ModificaPersonaPage () {

		add(new WizardModificaPersonaPanel("wizardModificaPersona"));
		add(new EsenzioniListPanel("listaEsenzioni"));
		add(new WizardScegliMedicoPanel("sceltaMedico"));
	}

}
