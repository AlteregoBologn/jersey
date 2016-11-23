package web.comp.cambiaPassword;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Persona;
import web.c.BasePanel;

public class ChangePwdPanel2 extends BasePanel {
	@Autowired
	@SpringBean 
	Manager manager;
	Persona persona = new Persona();

	public ChangePwdPanel2(String id, Persona p) {
		super(id);

		setOutputMarkupId(true);	
		persona = p;

		Form<?> form = new Form("form");

		PasswordTextField password = new PasswordTextField("password",new PropertyModel<String>(persona, "password"));
		password.setRequired(true);
		
		PasswordTextField confPassword = new PasswordTextField("confPassword",new PropertyModel<String>(persona, "password"));
		confPassword.setRequired(true);
		
		

		AjaxButton button = new AjaxButton("registraPwd") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				inviaPassword(password.getValue(), confPassword.getValue());
				target.add(ChangePwdPanel2.this);				
			}		
		};
		form.add(password, button);
		add(form);
	}
	public void inviaPassword (String password, String confPassword) {
		if (password == confPassword) {
			persona.setPassword(confPassword);
			manager.cambiaPassword(persona);
		}
	}

}
