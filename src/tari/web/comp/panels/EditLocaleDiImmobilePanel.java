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
import org.apache.wicket.spring.injection.annot.SpringBean;

import model.Decodifica;
import tari.logic.TariManagerExt;
import tari.modelExt.LocaleDiImmobile;
import tari.modelExt.PersonaTariCompleta;
import web.c.BasePanel;
import web.c.MyDropDown;
import web.comp.registrazione.RegistrazionePanel;

public class EditLocaleDiImmobilePanel extends BasePanel {
	
	@SpringBean
	TariManagerExt tariManagerExt;
	
	public EditLocaleDiImmobilePanel(String id, LocaleDiImmobile locale, PersonaTariCompleta pc, boolean onNew) {
		super(id);
		Form form = new Form("formLocale");
		add(form);		
		/*List<String> tipi = tariManagerExt.caricaDecodificaDiTipoLocale();
		form.add(new DropDownChoice<String>("tipo", new PropertyModel<>(locale, "locale.tipo"), tipi));*/
		
		PropertyModel<String> ddm=new PropertyModel<String>(locale, "locale.tipo");
		form.add(new MyDropDown<Decodifica>("tipo", ddm, "unid", "descrizione"){
			@Override
			public List<Decodifica> getList() {				
				return tariManagerExt.caricaDecodificaDiTipoLocale();
			}
		});
		
		
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
