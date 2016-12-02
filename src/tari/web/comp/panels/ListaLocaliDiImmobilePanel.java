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

import tari.logic.TariManagerExt;
import tari.modelExt.DichiarazioneDiPersonaTari;
import tari.modelExt.LocaleDiImmobile;
import tari.modelExt.PersonaTariCompleta;
import web.c.BasePanel;
import web.c.Grid;
	
public class ListaLocaliDiImmobilePanel extends BasePanel {
	
	@SpringBean
	TariManagerExt tariManagerExt;
	Panel editPanel;
	Panel newEditPanel;
	DichiarazioneDiPersonaTari dpt;
	public ListaLocaliDiImmobilePanel(String id, DichiarazioneDiPersonaTari dpt, PersonaTariCompleta pc) {
		super(id);	
		editPanel = new EmptyPanel("edit");
		add(editPanel);
		this.dpt = dpt;
		Form form = new Form ("form");
		add(form);
		form.add(new Grid<LocaleDiImmobile>("lista", true, true, true, true, true) {

			@Override
			public List<IColumn> getColumns() {
				List<IColumn> columns = new ArrayList();
				columns.add(new PropertyColumn(new Model<>("MQ"), "locale.mq"));				
				return columns;
			}

			@Override
			public List<LocaleDiImmobile> getList(int f, int c) {				
				return dpt.getDichiarazioneImmobile().getLocaliDiImmobile();
			}

			@Override
			public long getSize() {
				return dpt.getDichiarazioneImmobile().getLocaliDiImmobile().size();
			}
			
			@Override
			public void onNew(AjaxRequestTarget target) {
				LocaleDiImmobile ldi = tariManagerExt.createLocaleDiImmobile();
				editPanel.replaceWith(getEditPanel(target, ldi, pc, false));
				target.add(ListaLocaliDiImmobilePanel.this);
				editPanel = newEditPanel;
			}
			
			@Override
			public void onEdit(AjaxRequestTarget target, LocaleDiImmobile object) {
				 
				 editPanel.replaceWith(getEditPanel(target, object, pc, false));
				 target.add(ListaLocaliDiImmobilePanel.this);
				 editPanel = newEditPanel;
			}
		});	
	}
	private Panel getEditPanel(AjaxRequestTarget target, LocaleDiImmobile locali, PersonaTariCompleta pc, boolean onNew) {
		Panel p = new EditLocaleDiImmobilePanel("edit", locali, pc, onNew) {
			
			public void onAnnulla(AjaxRequestTarget target, LocaleDiImmobile l ){
				this.setVisible(false);
				target.add(ListaLocaliDiImmobilePanel.this);
			}
			public void onSalva(AjaxRequestTarget target, LocaleDiImmobile l ){
				l.setOperation(l.OP_UPDATE);
				dpt.getDichiarazioneImmobile().getLocaliDiImmobile().add(l);
				this.setVisible(false);
				target.add(ListaLocaliDiImmobilePanel.this);
			}
		};
		newEditPanel = p;
		return p;
	}
}
