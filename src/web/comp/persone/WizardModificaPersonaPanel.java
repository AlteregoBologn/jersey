package web.comp.persone;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Persona;
import web.Home;
import web.MySession;
import web.c.BasePanel;

public class WizardModificaPersonaPanel extends BasePanel {
	@Autowired
	@SpringBean
	Manager manager;
	Persona persona = new Persona();

	public WizardModificaPersonaPanel(String id) {
		super(id);
		persona = MySession.get().getUtente();

		List<Panel> listaPannelli = new ArrayList();

		listaPannelli.add(new VisualizzaPersonaPanel("visualizzaPersona", persona));

		listaPannelli.add(new ModificaPersonaPanel("modificaPersona", persona));

		add(new WizardPersonaPanel("wizardPersona1", listaPannelli) {
			@Override
			public void onConferma(AjaxRequestTarget target) {
				setVisible(false);
				manager.aggiornaPersona(persona);
				setResponsePage(Home.class);
			}
		});

	}
}
