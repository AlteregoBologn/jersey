package web.comp.esenzioni;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Esenzione;
import model.EsenzioneSearch;
import model.Medico;
import modelExt.EsenzioneDiPersona;
import modelExt.PersonaCompleta;
import web.c.BasePanel;
import web.c.Grid;

public class ListaEsenzioniPanel extends BasePanel {
	@Autowired
	@SpringBean(name="manager")
	Manager manager;
	
	EsenzioneSearch search=new EsenzioneSearch();

	public ListaEsenzioniPanel(String id, final PersonaCompleta pc) {
		super(id);
		
		setOutputMarkupId(true);

		add(new Grid<Esenzione>("lista") {
			
			@Override
			public List<IColumn> getColumns() {
				List<IColumn> columns = new ArrayList();
				columns.add(new PropertyColumn(new Model("unid"), "unid"));
				columns.add(new PropertyColumn(new Model("descrizione"), "descrizione"));
				
				return columns;
			}

			@Override
			public List<Esenzione> getList(int f, int c) {
				return manager.cercaEsenzioni(search);
			}

			@Override
			public long getSize() {
				return manager.countEsenzione(search);
			}
			
			
			@Override
			public void onSelect(AjaxRequestTarget target, Esenzione esenzione) {
				onSelezionaEsenzione(target,esenzione);
				target.add(ListaEsenzioniPanel.this);
				
			}

			@Override
			public void onDelete(AjaxRequestTarget target, Esenzione esenzione) {
				EsenzioneDiPersona esenzioneAttiva = new EsenzioneDiPersona();
				esenzioneAttiva.setEsenzione(esenzione);
				pc.getEsenzioni().remove(esenzioneAttiva);
				esenzioneAttiva.setOperation(esenzione.OP_DELETE);
				
				target.add(ListaEsenzioniPanel.this);
			}
			
		});
	}
	
	public void onSelezionaEsenzione(AjaxRequestTarget target, Esenzione esenzione) {

	}


}
