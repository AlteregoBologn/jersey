package web.comp.registrazione;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Citta;
import model.Persona;
import web.c.BasePanel;

public class RegistrazionePanel extends BasePanel {
	@Autowired
	@SpringBean(name="manager")
	Manager manager;
	Persona persona = new Persona();

	Citta citta = new Citta();

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
		
		final IModel<Citta> model = new PropertyModel<Citta>(
				
					/*@Override
					public void setObject(Citta object) {
						super.setObject(object);
						persona.setComunenascita(object.getNomecitta());
					},
					
					new AbstractAutoCompleteTextRenderer<Citta>() {

					@Override
					protected String getTextValue(Citta object) {
						return object.getNomecitta();
					}
				});
	

		final AutoCompleteTextField<Citta> comunenascita = new AutoCompleteTextField<Citta>("comunenascita", model) {


			@Override
			protected Iterator<Citta> getChoices(String input) {
				try {
					if (Strings.isEmpty(input)) {
						List<Citta> emptyList = Collections.emptyList();
						return emptyList.iterator();
					}
					CittaSearch cs = new CittaSearch();
					cs.setNomeCittaLike(input);
					List<Citta> ret = manager.cercaCitta(cs);
					return ret.iterator();
				} catch (Exception e) {
					e.printStackTrace();
					return new ArrayList<Citta>().iterator();
				}
			}
	
		};
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
	    form.add(comunenascita);
	    
		
		form.add(nome, cognome, codFisc, email, password, sesso, datanascitaField);

		add(form);
	}

}
