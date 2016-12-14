package tari.web.comp.panels;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
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
import web.c.MyRadioGroup;
import web.c.ProvaModale;

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
		
		// edit persona tari  panel
		// dentro ha anche allegati tari panel
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
		formAnagrafica.add(new EmailTextField("email", new PropertyModel<>(pc, "personaTari.email")));
		formAnagrafica.add(new EmailTextField("pec", new PropertyModel<>(pc, "personaTari.pec")));
		
		// add blocco persona giuridica panel
		add(new EditPersonaTariPanel("persona", pc,false){
			@Override
			public boolean isEnabled() {
				return false;
			}
		});
		
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
		
		
		Map checks=new HashMap();
		checks.put("unicooccupante","Abitazione con unico occupante residente");
		checks.put("agricoltore","Agricoltori che occupano la parte abitativa della costruzione rurale");
		checks.put("italianoallestero","Persona che risiede o dimora per più di 6 mesi all'anno in località al di fuori del territorio nazionale (Si dichiara che "
				+ "l'abitazione oggetto della presente denuncia è la propria abitazione principale e che non si intende cederla in locazione o comodato)");
		
		MyRadioGroup radios=new MyRadioGroup("radios", 
				new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazione"), 
				checks){
			@Override
			public boolean isRadio() {
				return false;
			}
		};
		formDichiaraInoltre.add(radios);

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

		DateTextField dataField2 = new DateTextField("datada",
				new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.datada"));
		DatePicker datePicker2 = new DatePicker();
		datePicker2.setShowOnFieldClick(true);
		datePicker2.setAutoHide(true);
		dataField2.add(datePicker2);
		dataField2.add(new CssBeahvior());
		formPrecDich.add(dataField2);
				
		formPrecDich.add(new TextField<String>("motivo", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.motivo")));
		formPrecDich.add(new TextArea<String>("comunicazione",new PropertyModel<>(EditDichiarazionePanel.this,"dichiarazione.precedenteDichiarazione.comunicazione")));
		
		
	
		
		add(formFirst);
	}

}

