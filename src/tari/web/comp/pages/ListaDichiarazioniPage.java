package tari.web.comp.pages;

import org.apache.wicket.spring.injection.annot.SpringBean;

import tari.logic.TariManagerExt;
import tari.modelExt.PersonaTariCompleta;
import tari.web.comp.panels.ListaDichiarazioniPanel;
import web.BasePage;

public class ListaDichiarazioniPage extends BasePage {

	@SpringBean
	TariManagerExt tariManagerExt;
	
	public ListaDichiarazioniPage () throws Exception {
		
		PersonaTariCompleta pc = tariManagerExt.loadPersonaTariCompleta(411);// TODO: persona.getUnid());
		add(new ListaDichiarazioniPanel("listaDichiarazioni", pc));
	}

}
