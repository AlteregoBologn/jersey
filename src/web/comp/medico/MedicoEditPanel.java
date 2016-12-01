package web.comp.medico;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import model.Medico;
import modelExt.EsenzioneDiPersona;

public class MedicoEditPanel extends Panel {

	public MedicoEditPanel(String id, Medico e, boolean isNew) {
		super(id);
		Form form = new Form("form");
		form.add(new TextField<>("descrizione", new PropertyModel<>(e, "esenzione.descrizione")));

		AjaxButton annulla = new AjaxButton("annulla") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				onAnnulla(target, e);
			}

		};
		form.add(annulla);

		AjaxButton salva = new AjaxButton("salva") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				onSalva(target, e);
			}

			@Override
			public boolean isVisible() {
				return isNew;
			}
		};
		form.add(salva);

		AjaxButton aggiorna = new AjaxButton("aggiorna") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				onAggiorna(target, e);
			}

			@Override
			public boolean isVisible() {
				return !isNew;
			}
		};
		form.add(aggiorna);
		add(form);
	}

	public void onSalva(AjaxRequestTarget t, Medico e) {

	}

	public void onAggiorna(AjaxRequestTarget t, Medico e) {

	}

	public void onAnnulla(AjaxRequestTarget t, Medico e) {

	}

}