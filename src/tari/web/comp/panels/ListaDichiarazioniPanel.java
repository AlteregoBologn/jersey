package tari.web.comp.panels;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import model.E;
import tari.logic.TariManagerExt;
import tari.modelExt.DichiarazioneDiPersonaTari;
import tari.modelExt.PersonaTariCompleta;
import web.c.BasePanel;
import web.c.Grid;
	
public class ListaDichiarazioniPanel extends BasePanel {
	
	@SpringBean
	TariManagerExt tariManagerExt;
	Panel editPanel;
	Panel newEditPanel;
	boolean selectVisible = false;// Per far comparire/scomparire i pannelli sotto il grid iniziale al clic del Select
	
	public ListaDichiarazioniPanel(String id, PersonaTariCompleta pc) {
		super(id);	
		editPanel = new EmptyPanel("edit");
		add(editPanel);
		
		Form form = new Form ("form");
		add(form);
		form.add(new Grid<DichiarazioneDiPersonaTari>("lista", true, true, false, false, false) {
			

			@Override
			public List<IColumn> getColumns() {
				List<IColumn> columns = new ArrayList();
				columns.add(new PropertyColumn(new Model<>("Data inserimento immobile"), "dichiarazione.data"));
				columns.add(new PropertyColumn(new Model<>("Via immobile"), "dichiarazioneImmobile.immobile.via"));				
				return columns;
			}

			@Override
			public List<DichiarazioneDiPersonaTari> getList(int f, int c) {		
				return pc.getDichiarazioniDiPersona();
			}

			@Override
			public long getSize() {
				return pc.getDichiarazioniDiPersona().size();
			}
			@Override
			public void onNew(AjaxRequestTarget target) {
				DichiarazioneDiPersonaTari n = tariManagerExt.createNuovaDichiarazioneDiPersonaTari();
				pc.setOperation(pc.OP_INSERT);
				n.setOperation(n.OP_INSERT);			
				editPanel.replaceWith(getEditPanel(target, n, pc, true));
				target.add(ListaDichiarazioniPanel.this);
				editPanel = newEditPanel;
			}
			/*
			@Override
			public void onEdit(AjaxRequestTarget target, DichiarazioneDiPersonaTari object) {
				 pc.setOperation(pc.OP_UPDATE);
				 object.setOperation(object.OP_UPDATE);
				 editPanel.replaceWith(getEditPanel(target, object, pc, false));
				 target.add(ListaDichiarazioniPanel.this);
				 editPanel = newEditPanel;
			}
			 */
			@Override
			public void onSelect(AjaxRequestTarget target, DichiarazioneDiPersonaTari object) {
				editPanel.replaceWith(getEditPanel(target, object, pc, false));				 
				target.add(ListaDichiarazioniPanel.this);
				editPanel = newEditPanel;
			}
		});	
	}
	private Panel getEditPanel(AjaxRequestTarget target, DichiarazioneDiPersonaTari dichiarazione, PersonaTariCompleta pc, boolean onNew) {
		Panel p = new EditDichiarazionePanel("edit", dichiarazione, pc, onNew);
		newEditPanel = p;
		return p;
	}
}
