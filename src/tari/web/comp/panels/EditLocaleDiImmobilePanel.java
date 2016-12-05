package tari.web.comp.panels;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import tari.modelExt.LocaleDiImmobile;
import tari.modelExt.PersonaTariCompleta;
import web.c.BasePanel;
import web.comp.registrazione.RegistrazionePanel;

public class EditLocaleDiImmobilePanel extends BasePanel {

	
	public EditLocaleDiImmobilePanel(String id, LocaleDiImmobile locale, PersonaTariCompleta pc, boolean onNew) {
		super(id);
		Form form = new Form("formLocale");
		add(form);		
		List<String> tipi = new ArrayList<String>();
		tipi.add("Superficie a filo dei muri dell'abitazione compresa mansarda, taverna e scale (esclusi balconi e terrazze scoperte)");
		tipi.add("Superficie delle cantine");
		tipi.add("Superficie dei garage e/o posto auto coperto");
		form.add(new DropDownChoice<String>("tipo", new PropertyModel<>(locale, "locale.tipo"), tipi));
		form.add(new TextField<String>("mq", new PropertyModel<>(locale, "locale.mq")));
		form.add(new TextField<String>("foglio", new PropertyModel<>(locale, "locale.foglio")));
		form.add(new TextField<String>("particella", new PropertyModel<>(locale, "locale.particella")));
		form.add(new TextField<String>("subalterno", new PropertyModel<>(locale, "locale.subalterno")));
		
		form.add(new AjaxButton("salva") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				onSalva(target, locale);
			}
		});
		form.add(new AjaxButton("annulla") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {				
				onAnnulla(target, locale);			
			}
		});
	}
	public void onAnnulla(AjaxRequestTarget target, LocaleDiImmobile l){
		
	}
	public void onSalva(AjaxRequestTarget target, LocaleDiImmobile l){
		
	}
}
