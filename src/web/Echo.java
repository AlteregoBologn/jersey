package web;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import model.Persona;
import web.comp.persone.PersonaListPanel;
import web.comp.persone.PersonaPanel;

public class Echo extends WebPage {
	String message = "ciao";
	String navigation = "Questa è la mainNavigation";

	public Echo() {
		PropertyModel<String> messageModel = new PropertyModel<>(this, "message");
		setOutputMarkupId(true);
		
		// The label displays the currently set message
		add(new Label("mainNavigation", new PropertyModel<>(this, "navigation")));
		add(new Label("msg", messageModel));
		
		add(new Label("welcome", new PropertyModel(this, "navigation"){
			@Override
			public Object getObject() {
				Persona p = MySession.get().getUtente();
				if(p!=null){
					return p.getNome()+" "+p.getCognome();
				}
				
				return "";
			}
		}));
		
		// Add a form to change the message. We don't need to do anything
		// else with this form as the shared model is automatically updated
		// on form submits
		Form<?> form = new Form("form");
		form.add(new TextField<>("msgInput", messageModel));

		form.add(new TextField<>("msgInput2", new PropertyModel<>(this, "navigation")));
		AjaxButton button = new AjaxButton("test") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				navigation = "ora esatta 	" + new Date();
				target.add(Echo.this);
			}
			
		};
		form.add(button);
		
		//
		PersonaListPanel listaPanel=new PersonaListPanel("listaPersone");
		add( listaPanel );
		 
		Persona p = new Persona();
		p.setNome("andrea");
		//
		add( new PersonaPanel("editPersona", p){
			public void onSalva(Persona persona, AjaxRequestTarget target) {
				//setVisible(false);
				target.add(listaPanel);
			}

		});
		//
		
		add(form);
	}

}
