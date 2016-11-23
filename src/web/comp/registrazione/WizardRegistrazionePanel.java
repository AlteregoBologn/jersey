package web.comp.registrazione;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Persona;
import web.Home;
import web.c.BasePanel;
import web.c.WizardPanel;
import web.comp.registrazione.ConfermaRegistrazionePanel;
import web.comp.registrazione.RegistrazioneEffettuataPanel;
import web.comp.registrazione.RegistrazionePanel;

public class WizardRegistrazionePanel extends BasePanel {
	@Autowired
	@SpringBean(name="manager")
	Manager manager;
	Persona persona = new Persona();
	//int pannelloAttivo = 0;

	public WizardRegistrazionePanel(String Id, Persona p) {
		
		super(Id);
		
		persona = p;

		List<Panel> listaPannelli = new ArrayList();
		
		listaPannelli.add(new RegistrazionePanel("registrazione", persona));
		
		listaPannelli.add(new ConfermaRegistrazionePanel("confermaRegistrazione", persona));
		
		listaPannelli.add(new RegistrazioneEffettuataPanel("regeffRegistrazione"));


		add(new WizardPanel("wizard", listaPannelli) {
			
			@Override
			public void onConferma(AjaxRequestTarget target) {
				setVisible(false);
				//manager.registraPersona(persona);
				setResponsePage(Home.class);
			}
			
			@Override
			public void onChangePanel(AjaxRequestTarget target, Integer attivo) {
				if (attivo == 2) {
					manager.registraPersona(persona);
					setShowButton(false);
				}				
			}
		});
	}
	

}
