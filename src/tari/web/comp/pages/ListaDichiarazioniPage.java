package tari.web.comp.pages;

import tari.web.comp.panels.ListaDichiarazioniPanel;
import web.BasePage;

public class ListaDichiarazioniPage extends BasePage {

	public ListaDichiarazioniPage () throws Exception {

		add(new ListaDichiarazioniPanel("listaDichiarazioni"));
	}

}
