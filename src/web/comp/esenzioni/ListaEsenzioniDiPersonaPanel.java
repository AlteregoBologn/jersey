package web.comp.esenzioni;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import modelExt.EsenzioneDiPersona;
import modelExt.PersonaCompleta;
import web.c.BasePanel;
import web.c.Grid;

public class ListaEsenzioniDiPersonaPanel extends BasePanel {
	Panel editPanel;
	Panel newEditPanel;
	

	public ListaEsenzioniDiPersonaPanel(String id, final PersonaCompleta pc) {
		super(id);

		editPanel = new EmptyPanel("edit");
		add(editPanel);

		add(new Grid<EsenzioneDiPersona>("lista") {

			@Override
			public List<IColumn> getColumns() {
				List<IColumn> columns = new ArrayList();
				columns.add(new PropertyColumn(new Model("unid"), "esenzione.unid"));
				columns.add(new PropertyColumn(new Model("descrizione"), "esenzione.descrizione"));
				columns.add(new PropertyColumn(new Model("dataDA"), "relazione.dataDa"));
				columns.add(new PropertyColumn(new Model("dataA"), "relazione.dataA"));
				return columns;
			}

			@Override
			public List<EsenzioneDiPersona> getList(int f, int c) {
				return pc.getEsenzioni().subList(f, f + c);
			}

			@Override
			public long getSize() {
				return pc.getEsenzioni().size();
			}

			@Override
			public void onEdit(AjaxRequestTarget target, EsenzioneDiPersona esenzia) {
				editPanel.replaceWith(getEditPanel(target, esenzia, pc, false));
				target.add(ListaEsenzioniDiPersonaPanel.this);
				editPanel = newEditPanel;
			}

			@Override
			public void onNew(AjaxRequestTarget target) {
				EsenzioneDiPersona esenzia = new EsenzioneDiPersona();
				editPanel.replaceWith(getEditPanel(target, esenzia, pc, true));
				target.add(ListaEsenzioniDiPersonaPanel.this);
				editPanel = newEditPanel;
			}

			@Override
			public void onDelete(AjaxRequestTarget target, EsenzioneDiPersona esenzia) {
				// pc.getEsenzioni().remove( object );
				esenzia.setOperation(esenzia.OP_DELETE);
				target.add(ListaEsenzioniDiPersonaPanel.this);
			}
		});

	}

	private Panel getEditPanel(AjaxRequestTarget target, EsenzioneDiPersona esenzia, PersonaCompleta pc, boolean onNew) {
		Panel p = new EsenzioneDiPersonaEditPanel("edit", esenzia, onNew) {

			@Override
			public void onSalva(AjaxRequestTarget target, EsenzioneDiPersona esenzia) {
				esenzia.setOperation(esenzia.OP_INSERT);
				pc.getEsenzioni().add(esenzia);
				target.add(ListaEsenzioniDiPersonaPanel.this);
				setVisible(false);
				//modale.close(target);
			}

			@Override
			public void onAggiorna(AjaxRequestTarget target, EsenzioneDiPersona esenzia) {
				esenzia.setOperation(esenzia.OP_UPDATE);
				target.add(ListaEsenzioniDiPersonaPanel.this);
				setVisible(false);
			}

			@Override
			public void onAnnulla(AjaxRequestTarget target, EsenzioneDiPersona esenzia) {
				esenzia.setOperation(esenzia.OP_DELETE);
				target.add(ListaEsenzioniDiPersonaPanel.this);
				setVisible(false);
			}
		};
		newEditPanel = p;
		return p;
	}

}
