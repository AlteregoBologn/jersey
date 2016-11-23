package web.comp.cambiaPassword;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Persona;
import web.c.BasePanel;

public class ChangePwdPanel extends BasePanel {
	@Autowired
	@SpringBean 
	Manager manager;
	Persona persona = new Persona();
	Boolean flag = true;

	public ChangePwdPanel(String id, Persona p) {
		
		super(id);

		setOutputMarkupId(true);	
		
		persona = p;

		Form<?> form = new Form("form");

		EmailTextField email = new EmailTextField("email",new PropertyModel<>(ChangePwdPanel.this, "persona.email"));
		email.setRequired(true);

		AjaxButton button = new AjaxButton("inviaPwd") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				// Invio email
				//if (flag == true)
				target.add(ChangePwdPanel.this);
			}

			/*@Override
			public boolean isVisible() {
				
				return 
			}*/
		};
		form.add(email, button);
		add(form);

	}

}
