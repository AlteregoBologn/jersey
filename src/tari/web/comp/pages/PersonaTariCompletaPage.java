package tari.web.comp.pages;

import org.apache.wicket.spring.injection.annot.SpringBean;

import model.Persona;
import tari.logic.TariManagerExt;
import tari.model.PersonaTari;
import tari.modelExt.PersonaTariCompleta;
import tari.web.comp.panels.EditPersonaTariPanel;
import web.BasePage;
import web.MySession;

public class PersonaTariCompletaPage extends BasePage {

	@SpringBean
	TariManagerExt tariManagerExt;
	
	public PersonaTariCompletaPage () throws Exception {
		
		Persona persona = MySession.get().getUtente();
		
		PersonaTariCompleta pc = tariManagerExt.loadPersonaTariCompleta(persona.getUnid());
		
		add(new EditPersonaTariPanel("persona", pc,false));
	}

}
