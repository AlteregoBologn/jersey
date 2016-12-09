package tari.web.comp.panels;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import model.Decodifica;
import tari.logic.TariManagerExt;
import tari.modelExt.DichiarazioneDiPersonaTari;
import tari.modelExt.PersonaTariCompleta;
import web.c.BasePanel;
import web.c.CssBeahvior;
import web.c.MyCheckBox;
import web.c.MyDropDown;

public class EditDichiarazionePanel extends BasePanel {
	
	@SpringBean
	TariManagerExt tariManagerExt;
	DichiarazioneDiPersonaTari dichiarazione;
	Decodifica decodifica;
	
	public EditDichiarazionePanel(String id, final DichiarazioneDiPersonaTari dichiarazione, PersonaTariCompleta pc, boolean onNew) {
		super(id);
		
		Form formFirst = new Form("formFirst");
		
		Form formAnagrafica = new Form("formAnagrafica"){
			@Override
			public boolean isEnabled() {
				return false;// Form contenente i dati Anagrafici, non sono modificabili da questa pagina
			}
		};
		add(formAnagrafica);
		this.dichiarazione=dichiarazione;
		
		formAnagrafica.add(new TextField<String>("nome", new PropertyModel<>(pc, "personaTari.nome")));
		formAnagrafica.add(new TextField<String>("cognome", new PropertyModel<>(pc, "personaTari.cognome")));
		formAnagrafica.add(new TextField<String>("comunenascita", new PropertyModel<>(pc, "personaTari.comunenascita")));
		formAnagrafica.add(new DateTextField("datanascita", new PropertyModel<>(pc, "personaTari.datanascita")));
		formAnagrafica.add(new DateTextField("comuneResidenza", new PropertyModel<>(pc, "residenza.comune")));
		formAnagrafica.add(new DateTextField("viaResidenza", new PropertyModel<>(pc, "residenza.via")));
		formAnagrafica.add(new DateTextField("civicoResidenza", new PropertyModel<>(pc, "residenza.civico")));
		formAnagrafica.add(new DateTextField("capResidenza", new PropertyModel<>(pc, "residenza.cap")));
		formAnagrafica.add(new TextField<String>("cf", new PropertyModel<>(pc, "personaTari.cf")));
		formAnagrafica.add(new DateTextField("recapito", new PropertyModel<>(pc, "personaTari.recapitoTelefonico")));
		//formAnagrafica.add(new TextField<String>("sesso", new PropertyModel<>(pc, "personaTari.sesso")));		
		formAnagrafica.add(new EmailTextField("email", new PropertyModel<>(pc, "personaTari.email")));
		formAnagrafica.add(new EmailTextField("pec", new PropertyModel<>(pc, "personaTari.pec")));
		
		
		Form formDichiara = new Form("formDichiara"){
			@Override
			public boolean isEnabled() {
				return onNew;
			}
		};
		formFirst.add(formDichiara);
		
		DateTextField dataField = new DateTextField("dataDa",
				new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.dataDa"));
		DatePicker datePicker = new DatePicker();
		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		dataField.add(datePicker);
		dataField.setRequired(true);
		dataField.add(new CssBeahvior());
		formDichiara.add(dataField);
		
		PropertyModel<Integer> ddm=new PropertyModel<Integer>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.qualita");
		formDichiara.add(new MyDropDown<Decodifica>("qualita", ddm, "unid", "descrizione"){
			@Override
			public List<Decodifica> getList() {				
				return tariManagerExt.caricaDecodificaDiQualitaDi();
			}
			/*
			@Override
			public void onChange(AjaxRequestTarget target) {
				dichiarazione.getDichiarazioneImmobile().getImmobile().setVia("pincopanco");
				target.add(formDichiara);
			}
			*/
		});

		
		formDichiara.add(new RequiredTextField<String>("via", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.via")));
		formDichiara.add(new RequiredTextField<String>("civico", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.civico")));
		formDichiara.add(new RequiredTextField<String>("interno", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.interno")));
		formDichiara.add(new RequiredTextField<String>("piano", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.piano")));
		formDichiara.add(new RequiredTextField<String>("nomeproprietario", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.nomeProprietario")));
		formDichiara.add(new RequiredTextField<String>("nomeprecedentedetentore", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.nomePrecedenteDetentore")));
		
		Form formLocali = new Form("formLocali"){
			@Override
			public boolean isEnabled() {
				return onNew;
			}
		};
		
		formLocali.add(new ListaLocaliDiImmobilePanel("listaLocali", dichiarazione, pc, onNew));
		
		formFirst.add(new AjaxButton("salva") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				tariManagerExt.saveDichiarazioneDiPersonaTari(dichiarazione, pc);
				formFirst.setVisible(false);	
				formAnagrafica.setVisible(false);
				target.add(EditDichiarazionePanel.this);							
			}
			
			@Override
			public boolean isVisible() {
				return onNew;
			}
		});
		formFirst.add(new AjaxButton("chiudi") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				formFirst.setVisible(false);	
				formAnagrafica.setVisible(false);
				target.add(EditDichiarazionePanel.this);							
			}
			
			@Override
			public boolean isVisible() {
				return !onNew;
			}

		});
		
		formFirst.add(formLocali);
		
		Form formDichiaraInoltre = new Form("formDichiaraInoltre"){
			@Override
			public boolean isEnabled() {				
				return onNew;
			}
		};
		formFirst.add(formDichiaraInoltre);
		/*PropertyModel propAgricoltore = new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazione.agricoltore");
		PropertyModel propEstero = new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazione.italianoallestero");
		PropertyModel propResidente = new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazione.unicooccupante");
		MyRadioGroup<Decodifica> mioRadio = new MyRadioGroup<Decodifica>("mioRadio", propAgricoltore, propEstero, propResidente, "unid", "descrizione"){
			@Override
			public List<Decodifica> getList() {
				return tariManagerExt.caricaDecodificaPosizioneDiDicharazione();
			}
		};
		formDichiaraInoltre.add(mioRadio);
		*/
		
		List<Decodifica> posizioni = tariManagerExt.caricaDecodificaPosizioneDiDicharazione();
		//formDichiaraInoltre.add(
		
		MyCheckBox agricoltore = new MyCheckBox("agricoltore",
				new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazione.agricoltore"), 
				new Model(posizioni.get(1).getDescrizione()));
		
		MyCheckBox italianoallestero = new MyCheckBox("italianoallestero",
				new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazione.italianoallestero"), 
				new Model(posizioni.get(2).getDescrizione()));
		
		MyCheckBox unico = new MyCheckBox("unicooccupante",
				new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazione.unicooccupante"), 
				new Model(posizioni.get(0).getDescrizione()));
		
		
		formDichiaraInoltre.add(unico, agricoltore, italianoallestero);

		/* 
		 * Metodo errato per i RadioButton (RadioChoice) ma l'unico che funziona correttamente sull'interfaccia.
		 * Non visualizza correttamente sul load ovviamente, il problema che ho riscontrato è stato
		 * il controllo di 3 proprietà e non solo una come ad esempio "qualità" di immobile.
		 * MyRadioGroup e MyCheckBoxGroup sono prove fallite per questo componente
		 */
		
		List<String> posizioniSBAGLIATO = tariManagerExt.caricaDecodificaPosizioneDiDicharazioneSBAGLIATO();
		
		RadioChoice<Object> posizione = new RadioChoice<Object>("posizione",
				new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazione.italianoallestero"), posizioniSBAGLIATO);
		posizione.setRequired(true);
		formDichiaraInoltre.add(posizione);
		
		
		Form formPrecDich = new Form("formPrecDich"){
			@Override
			public boolean isEnabled() {
				return onNew;
			}
		};
		formFirst.add(formPrecDich);
		
		formPrecDich.add(new TextField<String>("via", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.via")));
		formPrecDich.add(new TextField<String>("civico", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.civico")));
		formPrecDich.add(new TextField<String>("interno", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.interno")));
		//formPrecDich.add(new DateTextField("datada", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.datada")));

		DateTextField dataField2 = new DateTextField("datada",
				new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.datada"));
		DatePicker datePicker2 = new DatePicker();
		datePicker2.setShowOnFieldClick(true);
		datePicker2.setAutoHide(true);
		dataField2.add(datePicker2);
		dataField2.add(new CssBeahvior());
		formPrecDich.add(dataField2);
				
		formPrecDich.add(new TextField<String>("motivo", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.motivo")));
		
		add(formFirst);
	}

}

