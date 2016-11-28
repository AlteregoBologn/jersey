package web.comp.registrazione;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Persona;
import web.c.BasePanel;

public class ConfermaRegistrazionePanel extends BasePanel {
	@Autowired
	@SpringBean(name="manager")
	Manager manager;
	
	Persona persona = new Persona();
	
	public ConfermaRegistrazionePanel(String Id, Persona p) {
		
		super(Id);
		
		persona = p;
		
		setOutputMarkupId(true);
		
		add(new Label("nome", new PropertyModel<>(persona, "nome")));
		
		add(new Label("cognome", new PropertyModel<>(persona, "cognome")));
		add(new Label("codFisc", new PropertyModel<>(persona, "Cf")));
		add(new Label("email", new PropertyModel<>(persona, "email")));
		add(new Label("password", new PropertyModel<>(persona, "password")));
		add(new Label("sesso", new PropertyModel<>(persona, "sesso")));
		add(new Label("datanascita", new PropertyModel<>(persona, "datanascita")));
		
	}
	/*

	public void onSalva(Persona persona, AjaxRequestTarget target) {
		
		setVisible(false);
	}*/

	}
