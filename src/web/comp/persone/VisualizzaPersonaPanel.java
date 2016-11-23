package web.comp.persone;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;

import model.Persona;
import web.c.BasePanel;

public class VisualizzaPersonaPanel extends BasePanel {

	Persona persona = new Persona();

	public VisualizzaPersonaPanel(String id, Persona p) {
		super(id);

		persona = p;

		add(new Label("nome", new PropertyModel<>(persona, "nome")));

		add(new Label("cognome", new PropertyModel<>(persona, "cognome")));
		add(new Label("codFisc", new PropertyModel<>(persona, "Cf")));
		add(new Label("email", new PropertyModel<>(persona, "email")));
		add(new Label("password", new PropertyModel<>(persona, "password")));
		add(new Label("sesso", new PropertyModel<>(persona, "sesso")));
		add(new Label("datanascita", new PropertyModel<>(persona, "datanascita")));

	}
}
