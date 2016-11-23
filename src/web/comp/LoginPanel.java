package web.comp;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.xalan.xsltc.compiler.util.ErrorMessages_it;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Persona;
import web.Home;
import web.c.BasePanel;

public class LoginPanel extends BasePanel {
	 @Autowired
	 @SpringBean 
	Manager manager;
	String pwd="";
	String username="";

	Persona utente = new Persona();
	
	public LoginPanel(String id, Persona p) {
		super(id);
		
		utente = p;
		
		Form<?> form = new Form("form");		
		
		EmailTextField email = new EmailTextField("email",new PropertyModel<String>(this, "utente.email"));
		email.setRequired(true);
		
		PasswordTextField password = new PasswordTextField("password",new PropertyModel<String>(this,"utente.password"));
		password.setRequired(true);
		
		form.add(email, password);
		
		
		
		
		AjaxButton button = new AjaxButton("login") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {			
				manager.login(utente.getEmail(), utente.getPassword());
				target.add(LoginPanel.this);
				setResponsePage(Home.class);
			}		
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(LoginPanel.this);
			}
		};
		form.add(button);
		add(form);
	}

}
