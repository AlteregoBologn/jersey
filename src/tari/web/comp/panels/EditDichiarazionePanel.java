package tari.web.comp.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tari.logic.TariManagerExt;
import tari.modelExt.DichiarazioneDiPersonaTari;
import tari.modelExt.PersonaTariCompleta;
import web.c.BasePanel;

public class EditDichiarazionePanel extends BasePanel {
	
	@SpringBean
	TariManagerExt tariManagerExt;
	DichiarazioneDiPersonaTari dichiarazione;
	
	public EditDichiarazionePanel(String id, final DichiarazioneDiPersonaTari dichiarazione, PersonaTariCompleta pc, boolean onNew) {
		super(id);
		Form form = new Form("formEdit"){
			@Override
			public boolean isEnabled() {
				return true;// TODO disable!!
			}
		};
		add(form);
		this.dichiarazione=dichiarazione;
		
		form.add(new TextField<String>("nome", new PropertyModel<>(pc, "personaTari.nome")));
		form.add(new TextField<String>("cognome", new PropertyModel<>(pc, "personaTari.cognome")));
		form.add(new TextField<String>("cf", new PropertyModel<>(pc, "personaTari.cf")));
		form.add(new TextField<String>("sesso", new PropertyModel<>(pc, "personaTari.sesso")));
		form.add(new DateTextField("datanascita", new PropertyModel<>(pc, "personaTari.datanascita")));
		form.add(new TextField<String>("comunenascita", new PropertyModel<>(pc, "personaTari.comunenascita")));
		form.add(new EmailTextField("email", new PropertyModel<>(pc, "personaTari.email")));
		
		form.add(new TextField<String>("via", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.via")));
		
		Form form2 = new Form("form2");
		
		form2.add(new ListaLocaliDiImmobilePanel("listaLocali", dichiarazione, pc));
		
		form2.add(new AjaxButton("salva") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				tariManagerExt.saveImmobileDiDichiarazione(dichiarazione, pc);

				target.add(EditDichiarazionePanel.this);
			}
		});
		add(form2);
	}

}
