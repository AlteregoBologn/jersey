package web.comp.persone;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Persona;

public class PersonaPanel extends Panel {
	 @Autowired
	 @SpringBean 
	Manager manager;
	
	Persona persona=new Persona();
	String infos="";
	
	public PersonaPanel(String id,Persona p) {
		super(id);
		
		persona=p;
		
		setOutputMarkupId(true);
		
		add(new Label("infos", new PropertyModel<>(this, "infos")));
		
		Form<?> form = new Form("form");

		form.add(new TextField<>("nome", new PropertyModel<>(PersonaPanel.this, "persona.nome")));
		form.add(new TextField<>("cognome", new PropertyModel<>(PersonaPanel.this, "persona.cognome")));
		
		AjaxButton button = new AjaxButton("salva") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				infos="salvo "+manager;
				manager.registraPersona(persona);
				target.add(PersonaPanel.this);
				onSalva(persona,target);
			}
			
		};
		form.add(button);
		add(form);
	}

	public void onSalva(Persona persona, AjaxRequestTarget target) {
		
		setVisible(false);
	}

}
