package web.comp.persone;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.ManagerExt;
import modelExt.PersonaCompleta;
import web.c.BasePanel;
import web.c.CssBeahvior;
import web.comp.esenzioni.ListaEsenzioniDiPersonaPanel;
import web.comp.medico.ScegliMedicoDiPersonaPanel;

public class ModificaPersonaCompletaPanel extends BasePanel {
	@Autowired
	@SpringBean(name = "managerExt")
	ManagerExt managerExt;
	
	PersonaCompleta persona = new PersonaCompleta();

	public ModificaPersonaCompletaPanel(String id, PersonaCompleta p) {
		super(id);
		
		persona = p;

		Form<?> form = new Form("form");

		form.add(new TextField<Integer>("unid", new PropertyModel<>(ModificaPersonaCompletaPanel.this, "persona.persona.unid")) {
			@Override
			public boolean isEnabled() {
				return false;
			}
		});
		form.add(new TextField<>("nome", new PropertyModel<>(ModificaPersonaCompletaPanel.this, "persona.persona.nome")));
		form.add(new TextField<>("cognome", new PropertyModel<>(ModificaPersonaCompletaPanel.this, "persona.persona.cognome")));
		form.add(new TextField<>("cf", new PropertyModel<>(ModificaPersonaCompletaPanel.this, "persona.persona.cf")));
		form.add(new EmailTextField("email", new PropertyModel<>(ModificaPersonaCompletaPanel.this, "persona.persona.email")) {
			@Override
			public boolean isEnabled() {
				return false;
			}
		});

		DateTextField datanascitaField = new DateTextField("datanascita",
				new PropertyModel(ModificaPersonaCompletaPanel.this, "persona.persona.datanascita"),
				new StyleDateConverter("S-", true));
		DatePicker datePicker = new DatePicker();
		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		datanascitaField.add(datePicker);
		datanascitaField.setRequired(true);
		datanascitaField.add(new CssBeahvior());
		form.add(datanascitaField);
		
		
		form.add(new ListaEsenzioniDiPersonaPanel("listaEsenzioni", persona));
		form.add(new ScegliMedicoDiPersonaPanel("sceltaMedico", persona));

		AjaxButton button = new AjaxButton("salva") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				managerExt.savePersonaCompleta(persona);
				target.add(ModificaPersonaCompletaPanel.this);
				onSalva(target, persona);
			}

		};
		form.add(button);

		add(form);
	}

	public void onSalva(AjaxRequestTarget target, PersonaCompleta persona) {

	}

}
