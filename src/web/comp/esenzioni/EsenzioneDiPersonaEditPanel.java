package web.comp.esenzioni;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import modelExt.EsenzioneDiPersona;

public class EsenzioneDiPersonaEditPanel extends Panel {

	public EsenzioneDiPersonaEditPanel(String id, EsenzioneDiPersona e, boolean isNew) {
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

	public void onSalva(AjaxRequestTarget t, EsenzioneDiPersona e) {

	}

	public void onAggiorna(AjaxRequestTarget t, EsenzioneDiPersona e) {

	}

	public void onAnnulla(AjaxRequestTarget t, EsenzioneDiPersona e) {

	}

}
