package web.comp.persone;

import java.util.ArrayList;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import model.Persona;
import web.c.BasePanel;
import web.c.CssBeahvior;

public class ModificaPersonaPanel extends BasePanel {

	Persona persona = new Persona();

	public ModificaPersonaPanel(String id, Persona p) {
		super(id);

		persona = p;

		TextField<String> nome = new TextField<String>("nome",
				new PropertyModel<>(ModificaPersonaPanel.this, "persona.nome")) {
			@Override
			protected void onComponentTag(ComponentTag tag) {

				super.onComponentTag(tag);
				String cssClass = hasErrorMessage() ? "error" : "";
				tag.append("class", cssClass, " ");
			}
		};

		nome.setRequired(true);

		TextField<String> cognome = new TextField<String>("cognome",
				new PropertyModel<>(ModificaPersonaPanel.this, "persona.cognome"));
		cognome.setRequired(true);
		cognome.add(new CssBeahvior());

		TextField<String> codFisc = new TextField<String>("codFisc",
				new PropertyModel<>(ModificaPersonaPanel.this, "persona.cf"));
		codFisc.setRequired(true);
		codFisc.add(new CssBeahvior());

		EmailTextField email = new EmailTextField("email",
				new PropertyModel<>(ModificaPersonaPanel.this, "persona.email"));
		email.setRequired(true);
		email.add(new CssBeahvior());

		PasswordTextField password = new PasswordTextField("password",
				new PropertyModel<>(ModificaPersonaPanel.this, "persona.password"));
		password.setRequired(true);
		password.add(new CssBeahvior());

		ArrayList<String> sessoList = new ArrayList<String>();
		sessoList.add("M");
		sessoList.add("F");
		sessoList.add("Altro");
		RadioChoice<String> sesso = new RadioChoice<String>("sessoGroup",
				new PropertyModel<>(ModificaPersonaPanel.this, "persona.sesso"), sessoList);
		sesso.setRequired(true);
		sesso.add(new CssBeahvior());

		DateTextField datanascitaField = new DateTextField("datanascita",
				new PropertyModel<>(ModificaPersonaPanel.this, "persona.datanascita"),
				new StyleDateConverter("S-", true));
		DatePicker datePicker = new DatePicker();
		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		datanascitaField.add(datePicker);
		datanascitaField.setRequired(true);
		datanascitaField.add(new CssBeahvior());

		add(nome, cognome, codFisc, email, password, sesso, datanascitaField);
	}
}

