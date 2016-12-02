package web.comp.esenzioni;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.ManagerExt;
import model.Esenzione;
import model.Medico;
import modelExt.EsenzioneDiPersona;
import modelExt.PersonaCompleta;
import web.c.BasePanel;
import web.c.CssBeahvior;
import web.c.Grid;
import web.comp.medico.ListaMediciDiPersonaPanel;
import web.comp.medico.ListaMediciPanel;
import web.comp.medico.ScegliMedicoDiPersonaPanel;
import web.comp.registrazione.RegistrazionePanel;

public class ListaEsenzioniDiPersonaPanel extends BasePanel {
	@Autowired
	@SpringBean(name = "managerExt")
	ManagerExt managerExt;

	EsenzioneDiPersona esenzioneAttiva;
	ListaEsenzioniPanel esenzioniPanel;

	public ListaEsenzioniDiPersonaPanel(String id, final PersonaCompleta pc) {
		super(id);

		setOutputMarkupId(true);

		esenzioneAttiva = managerExt.getEsenzioneAttiva(pc);

		Form<?> form = new Form("form") {
			@Override
			public boolean isEnabled() {
				return false;
			}
		};

		form.add(new TextField<>("unid", new PropertyModel<String>(ListaEsenzioniDiPersonaPanel.this, "esenzioneAttiva.esenzione.unid")));
		form.add(new TextField<>("descrizione", new PropertyModel<String>(ListaEsenzioniDiPersonaPanel.this, "esenzioneAttiva.esenzione.descrizione")));
		DateTextField datanascitaFieldA = new DateTextField("dataA", new PropertyModel(ListaEsenzioniDiPersonaPanel.this, "esenzioneAttiva.relazione.dataA"), new StyleDateConverter("S-", true));
		DatePicker datePickerA = new DatePicker();
		datePickerA.setShowOnFieldClick(true);
		datePickerA.setAutoHide(true);
		datanascitaFieldA.add(datePickerA);
		datanascitaFieldA.setRequired(true);
		datanascitaFieldA.add(new CssBeahvior());
		form.add(datanascitaFieldA);

		DateTextField datanascitaFieldDa = new DateTextField("dataDa", new PropertyModel(ListaEsenzioniDiPersonaPanel.this, "esenzioneAttiva.relazione.dataDa"), new StyleDateConverter("S-", true));
		DatePicker datePickerDa = new DatePicker();
		datePickerDa.setShowOnFieldClick(true);
		datePickerDa.setAutoHide(true);
		datanascitaFieldDa.add(datePickerDa);
		datanascitaFieldDa.setRequired(true);
		datanascitaFieldDa.add(new CssBeahvior());
		form.add(datanascitaFieldDa);

		add(new Grid<EsenzioneDiPersona>("lista") {

			@Override
			public List<IColumn> getColumns() {
				List<IColumn> columns = new ArrayList();
				columns.add(new PropertyColumn(new Model("Unid"), "esenzione.unid"));
				columns.add(new PropertyColumn(new Model("Descrizione"), "esenzione.descrizione"));
				columns.add(new PropertyColumn(new Model("data Da"), "relazione.dataDa"));
				columns.add(new PropertyColumn(new Model("data A"), "relazione.dataA"));
				return columns;
			}

			@Override
			public List<EsenzioneDiPersona> getList(int f, int c) {
				return pc.getEsenzioni();
			}

			@Override
			public long getSize() {
				return pc.getEsenzioni().size();
			}
		});
		AjaxButton scegli = new AjaxButton("scegli") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				esenzioniPanel.setVisible(true);
				target.add(ListaEsenzioniDiPersonaPanel.this);
			}

		};
		add(scegli);

		esenzioniPanel = new ListaEsenzioniPanel("esenzioni", pc) {
			public void onSelezionaEsenzione(AjaxRequestTarget target, Esenzione esenzione) {

				esenzioniPanel.setVisible(false);

				esenzioneAttiva = managerExt.selezionaEsenzione(pc, esenzione);
				target.add(ListaEsenzioniDiPersonaPanel.this);

			}
		};
		esenzioniPanel.setVisible(false);
		add(esenzioniPanel);
		add(form);

	}
}