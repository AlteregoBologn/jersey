package tari.web.comp.pages;

import org.apache.wicket.spring.injection.annot.SpringBean;

import model.Persona;
import tari.logic.TariManagerExt;
import tari.modelExt.PersonaTariCompleta;
import tari.web.comp.panels.EditPersonaTariPanel;
import web.BasePage;
import web.MySession;

public class PersonaTariCompletaPage extends BasePage {

	@SpringBean
	TariManagerExt tariManagerExt;
	
	public PersonaTariCompletaPage () throws Exception {
		
		Persona p=MySession.get().getUtente();
		
		PersonaTariCompleta pc = tariManagerExt.loadPersonaTariCompleta(p.getUnid());// TODO: persona.getUnid());
		
		add(new EditPersonaTariPanel("persona", pc,false));
	}

}
