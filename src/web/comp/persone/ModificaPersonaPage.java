package web.comp.persone;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.spring.injection.annot.SpringBean;

import web.BasePage;
import logic.ManagerExt;
import model.Persona;
import model.PersonaSearch;
import modelExt.PersonaCompleta;
import web.BasePage;
import web.MySession;

public class ModificaPersonaPage extends BasePage {
	@SpringBean
	ManagerExt managerExt;
	
	public ModificaPersonaPage () {

		PersonaCompleta pc = managerExt.loadPersonaCompleta(29);// TODO: persona.getUnid());
		
		add(new ModificaPersonaCompletaPanel("modificaPersona", pc){
			@Override
			public void onSalva(AjaxRequestTarget target, PersonaCompleta persona) {
				
			}
		});
		

	}

}
