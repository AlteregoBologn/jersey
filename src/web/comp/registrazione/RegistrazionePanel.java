package web.comp.registrazione;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AbstractAutoCompleteTextRenderer;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Citta;
import model.CittaSearch;
import model.Persona;
import web.c.BasePanel;
import web.c.CssBeahvior;
import web.c.MyAutoComplete;

public class RegistrazionePanel extends BasePanel {
	@Autowired
	@SpringBean(name="manager")
	Manager manager;
	Persona persona = new Persona();

	Citta citta = new Citta("Binasco");

	public RegistrazionePanel(String Id, Persona p) {
		super(Id);

		persona = p;

		setOutputMarkupId(true);

		Form<?> form = new Form("form") {

		};

		TextField<String> nome = new TextField<String>("nome",
				new PropertyModel<>(RegistrazionePanel.this, "persona.nome")){
			@Override
			protected void onComponentTag(ComponentTag tag) {
				
				super.onComponentTag(tag);
				String cssClass = hasErrorMessage() ? "error" : "";
				tag.append("class", cssClass, " ");
			}
		};
		nome.setRequired(true);

		TextField<String> cognome = new TextField<String>("cognome",
				new PropertyModel<>(RegistrazionePanel.this, "persona.cognome"));
		cognome.setRequired(true);
		cognome.add(new CssBeahvior());
		
		TextField<String> codFisc = new TextField<String>("codFisc",
				new PropertyModel<>(RegistrazionePanel.this, "persona.cf"));
		codFisc.setRequired(true);
		codFisc.add(new CssBeahvior());
		
		EmailTextField email = new EmailTextField("email",
				new PropertyModel<>(RegistrazionePanel.this, "persona.email"));
		email.setRequired(true);
		email.add(new CssBeahvior());

		PasswordTextField password = new PasswordTextField("password",
				new PropertyModel<>(RegistrazionePanel.this, "persona.password"));
		password.setRequired(true);
		password.add(new CssBeahvior());

		ArrayList<String> sessoList = new ArrayList<String>();
		sessoList.add("M");
		sessoList.add("F");
		sessoList.add("Altro");
		RadioChoice<String> sesso = new RadioChoice<String>("sessoGroup",
				new PropertyModel<>(RegistrazionePanel.this, "persona.sesso"), sessoList);
		sesso.setRequired(true);
		sesso.add(new CssBeahvior());

		
		DateTextField datanascitaField = new DateTextField("datanascita",
				new PropertyModel<>(RegistrazionePanel.this, "persona.datanascita"),
				new StyleDateConverter("S-", true));
		DatePicker datePicker = new DatePicker();
		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		datanascitaField.add(datePicker);
		datanascitaField.setRequired(true);
		datanascitaField.add(new CssBeahvior());
		
		final IModel<String> model = new PropertyModel<String>(RegistrazionePanel.this, "persona.comunenascita"){
				
					@Override
					public void setObject(String object) {
						super.setObject(object);
						//persona.setComunenascita(((Citta)object).getNomecitta());
					}
		};
					
					
		MyAutoComplete<Citta> a=new MyAutoComplete<Citta>("cercacitta", new PropertyModel<Citta>(RegistrazionePanel.this,"citta"), new Model("citta"), "citta"){
			@Override
			public List<Citta> getList(String input) {
				CittaSearch cs = new CittaSearch();
				cs.setNomeCittaLike(input);
				List<Citta> ret = manager.cercaCitta(cs);
				return ret;
			}
			@Override
			public String typeToString(Citta a) {
				return a.getNomecitta();
			}
			
		};
		form.add(a);
	
		final AutoCompleteTextField<String> comunenascita = new AutoCompleteTextField<String>("comunenascita", model ) {


			@Override
			protected Iterator<String> getChoices(String input) {
				ArrayList<String> rett=new ArrayList<String>();
				try {
					
					
					if (Strings.isEmpty(input)) {
						return rett.iterator();
					}
					
					CittaSearch cs = new CittaSearch();
					cs.setNomeCittaLike(input);
					List<Citta> ret = manager.cercaCitta(cs);
					
					for(Citta c:ret){
						rett.add(c.getNomecitta());
					}
					return rett.iterator();
				} catch (Exception e) {
					e.printStackTrace();
					return rett.iterator();
				}
			}
	
		};
		/*
		comunenascita.add(new AjaxFormComponentUpdatingBehavior("onchange") {

	        @Override
	        protected void onUpdate(AjaxRequestTarget target) {
	            System.out.println( "Value: " + comunenascita.getValue() );
	            target.add(RegistrazionePanel.this);
	        }	        

	        @Override
	        protected void onError(AjaxRequestTarget target, RuntimeException e) {

	        	super.onError(target, e);
	        	target.add(RegistrazionePanel.this);
	        	target.add(getFeedbackPanel());
	        }
	    });
	    */
	    form.add(comunenascita);
	    
		
		form.add(nome, cognome, codFisc, email, password, sesso, datanascitaField);

		add(form);
	}

}
