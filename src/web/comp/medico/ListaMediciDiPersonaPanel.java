package web.comp.medico;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import logic.ManagerExt;
import model.Medico;
import model.MedicoSearch;
import model.Rel_Persona_Medico;
import modelExt.EsenzioneDiPersona;
import modelExt.MedicoDiPersona;
import modelExt.PersonaCompleta;
import web.c.BasePanel;
import web.c.Grid;
import web.comp.esenzioni.EsenzioneDiPersonaEditPanel;
import web.comp.esenzioni.ListaEsenzioniDiPersonaPanel;

public class ListaMediciDiPersonaPanel extends BasePanel {
	@Autowired
	@SpringBean(name = "manager")
	Manager manager;

	Panel editPanel;
	Panel newEditPanel;
	MedicoSearch search = new MedicoSearch();

	public ListaMediciDiPersonaPanel(String id, final PersonaCompleta pc) {
		super(id);
		setOutputMarkupId(true);

		add(new Grid<MedicoDiPersona>("lista") {

			@Override
			public List<IColumn> getColumns() {
				List<IColumn> columns = new ArrayList();
				columns.add(new PropertyColumn(new Model("unid"), "medico.unid"));
				columns.add(new PropertyColumn(new Model("nome"), "medico.nome"));
				columns.add(new PropertyColumn(new Model("cognome"), "medico.cognome"));
				columns.add(new PropertyColumn(new Model("cf"), "medico.cf"));
				columns.add(new PropertyColumn(new Model("Da"), "relazione.dataDa"));
				columns.add(new PropertyColumn(new Model("A"), "relazione.dataA"));
				return columns;
			}

			@Override
			public List<MedicoDiPersona> getList(int f, int c) {
				return pc.getMedici();
			}

			@Override
			public long getSize() {
				return pc.getMedici().size();
			}

			@Override
			public void onDelete(AjaxRequestTarget target, MedicoDiPersona object) {
				
				pc.getMedici().remove(object);
				target.add(ListaMediciDiPersonaPanel.this);
			}
		});

	}

}
