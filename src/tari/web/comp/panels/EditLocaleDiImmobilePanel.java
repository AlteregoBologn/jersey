package tari.web.comp.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import tari.modelExt.LocaleDiImmobile;
import tari.modelExt.PersonaTariCompleta;
import web.c.BasePanel;

public class EditLocaleDiImmobilePanel extends BasePanel {

	
	public EditLocaleDiImmobilePanel(String id, final LocaleDiImmobile locale, PersonaTariCompleta pc, boolean onNew) {
		super(id);
		Form form = new Form("formLocale");
		add(form);		
		form.add(new TextField<String>("mq", new PropertyModel<>(locale, "locale.mq")));
		form.add(new AjaxButton("salva") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				onSalva(target, locale);
			}
		});
		form.add(new AjaxButton("annulla") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				onAnnulla(target, locale);			}
		});
	}
	public void onAnnulla(AjaxRequestTarget target, LocaleDiImmobile l ){
		
	}
	public void onSalva(AjaxRequestTarget target, LocaleDiImmobile l ){
		
	}
}
