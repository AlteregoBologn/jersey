package tari.web.comp.panels;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import model.Decodifica;
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
		
		Form formAnagrafica = new Form("formAnagrafica"){
			@Override
			public boolean isEnabled() {
				return false;// TODO disable!!
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
				return onNew;// TODO disable!!
			}
		};
		add(formDichiara);
		formDichiara.add(new DateTextField("dataDa", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.datada")));
		formDichiara.add(new TextField<String>("qualita", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.qualita")));
		formDichiara.add(new TextField<String>("via", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.via")));
		formDichiara.add(new TextField<String>("civico", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.civico")));
		formDichiara.add(new TextField<String>("interno", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.interno")));
		formDichiara.add(new TextField<String>("piano", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.piano")));
		formDichiara.add(new TextField<String>("nomeproprietario", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.nomeproprietario")));
		formDichiara.add(new TextField<String>("nomeprecedentedetentore", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazioneImmobile.immobile.nomeprecedentedetentore")));
		
		Form formLocali = new Form("formLocali"){
			@Override
			public boolean isEnabled() {
				return onNew;
			}
		};
		
		formLocali.add(new ListaLocaliDiImmobilePanel("listaLocali", dichiarazione, pc, onNew));
		
		formLocali.add(new AjaxButton("salva") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				tariManagerExt.saveImmobileDiDichiarazione(dichiarazione, pc);
				target.add(EditDichiarazionePanel.this);
			}
		});
		add(formLocali);
		
		Form formDichiaraInoltre = new Form("formDichiaraInoltre"){
			@Override
			public boolean isEnabled() {				
				return true;// TODO onNew;
			}
		};
		add(formDichiaraInoltre);
		
		List<String> posizioni = tariManagerExt.caricaDecodificaPoisizioneDiDicharazione();
		
		formDichiaraInoltre.add(new RadioChoice<String>("posizione", 
				new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.dichiarazione.agricoltore"), posizioni));
		
		
		Form formPrecDich = new Form("formPrecDich"){
			@Override
			public boolean isEnabled() {
				return true;// TODO onNew;
			}
		};
		add(formPrecDich);
		
		formPrecDich.add(new TextField<String>("via", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.via")));
		formPrecDich.add(new TextField<String>("civico", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.civico")));
		formPrecDich.add(new TextField<String>("interno", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.interno")));
		formPrecDich.add(new DateTextField("datada", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.datada")));
		formPrecDich.add(new TextField<String>("motivo", new PropertyModel<>(EditDichiarazionePanel.this, "dichiarazione.precedenteDichiarazione.motivo")));
		
	}

}

