package web.comp.medico;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Medico;
import model.MedicoSearch;
import modelExt.EsenzioneDiPersona;
import modelExt.PersonaCompleta;
import web.c.BasePanel;
import web.c.Grid;
import web.comp.esenzioni.EsenzioneDiPersonaEditPanel;
import web.comp.esenzioni.ListaEsenzioniDiPersonaPanel;

public class ListaMediciPanel extends BasePanel {
	@Autowired
	@SpringBean(name="manager")
	Manager manager;
	
	Panel editPanel;
	Panel newEditPanel;
	MedicoSearch search=new MedicoSearch();
	
	public ListaMediciPanel(String id) {
		super(id);
		

		add(new Grid<Medico>("lista") {

			@Override
			public List<IColumn> getColumns() {
				List<IColumn> columns = new ArrayList();
				columns.add(new PropertyColumn(new Model("unid"), "medico.unid"));
				columns.add(new PropertyColumn(new Model("nome"), "medico.nome"));
				columns.add(new PropertyColumn(new Model("cognome"), "medico.cognome"));
				columns.add(new PropertyColumn(new Model("cf"), "medico.cf"));
				columns.add(new PropertyColumn(new Model("dataDa"), "relazione.dataDa"));
				columns.add(new PropertyColumn(new Model("dataA"), "relazione.dataA"));
				return columns;
			}

			@Override
			public List<Medico> getList(int f, int c) {
				return manager.cercaMedico(search);
			}

			@Override
			public long getSize() {
				return manager.countMedico(search);
			}

			@Override
			public void onEdit(AjaxRequestTarget target, Medico m) {
				editPanel.replaceWith(getEditPanel(target, m, false));
				target.add(ListaMediciPanel.this);
				editPanel = newEditPanel;
			}
			
			@Override
			public void onSelect(AjaxRequestTarget target, Medico m) {
				
				target.add(ListaMediciPanel.this);
				onScegliMedico(target, m);
			}

			@Override
			public void onNew(AjaxRequestTarget target) {
				Medico m = new Medico();
				editPanel.replaceWith(getEditPanel(target, m,  true));
				target.add(ListaMediciPanel.this);
				editPanel = newEditPanel;
			}

			@Override
			public void onDelete(AjaxRequestTarget target, Medico m) {
				
				target.add(ListaMediciPanel.this);
			}
		});

	}

	private Panel getEditPanel(AjaxRequestTarget target, Medico m, boolean onNew) {
		Panel p = new MedicoEditPanel("edit", m, onNew) {

			@Override
			public void onSalva(AjaxRequestTarget target, Medico m) {
				manager.aggiornaMedico(m);
				setVisible(false);
				target.add(ListaMediciPanel.this);
			}

			@Override
			public void onAggiorna(AjaxRequestTarget target, Medico esenzia) {
				m.setCanc("S");
				manager.aggiornaMedico(m);
				
				target.add(ListaMediciPanel.this);
				setVisible(false);
			}

			@Override
			public void onAnnulla(AjaxRequestTarget target, Medico esenzia) {
				
				target.add(ListaMediciPanel.this);
				setVisible(false);
			}
		};
		newEditPanel = p;
		
		return p;
	
	}
	
	public void onScegliMedico(AjaxRequestTarget t,Medico m) {
		
	}

}
