package web.comp.medico;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Persona;
import web.MySession;
import web.c.BasePanel;

public class WizardScegliMedicoPanel extends BasePanel {
	@Autowired
	@SpringBean
	Manager manager;

	Persona persona = new Persona();

	public WizardScegliMedicoPanel(String id) {
		super(id);
		persona = MySession.get().getUtente();

		List<Panel> listaPannelli = new ArrayList();

		listaPannelli.add(new VisualizzaMedicoPanel("visualizzaMedico"));

		listaPannelli.add(new ScegliMedicoPanel("scegliMedico", persona));

		add(new WizardMedicoPanel("wizard", listaPannelli) {
			@Override
			public void onConferma(AjaxRequestTarget target) {
				setVisible(false);

			}
		});
	}

}

