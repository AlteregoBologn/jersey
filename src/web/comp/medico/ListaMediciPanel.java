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

public class ListaMediciPanel extends BasePanel {
	@Autowired
	@SpringBean(name="manager")
	Manager manager;
	
	Panel editPanel;
	Panel newEditPanel;
	MedicoSearch search=new MedicoSearch();
	
	public ListaMediciPanel(String id, final PersonaCompleta pc) {
		super(id);
		setOutputMarkupId(true);

		add(new Grid<Medico>("lista") {
			
			@Override
			public List<IColumn> getColumns() {
				List<IColumn> columns = new ArrayList();
				columns.add(new PropertyColumn(new Model("unid"), "unid"));
				columns.add(new PropertyColumn(new Model("nome"), "nome"));
				columns.add(new PropertyColumn(new Model("cognome"), "cognome"));
				columns.add(new PropertyColumn(new Model("cf"), "cf"));
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
			public void onSelect(AjaxRequestTarget target, Medico medico) {
				onScegliMedico(target,medico);
//				MedicoDiPersona mdp = new MedicoDiPersona();
//				mdp.setMedico(medico);
//				pc.getMedici().add(mdp);
//				mdp.setOperation(medico.OP_INSERT);
//				System.out.println(mdp.getOperation());
				target.add(ListaMediciPanel.this);
				
			}

			@Override
			public void onDelete(AjaxRequestTarget target, Medico medico) {
				MedicoDiPersona medicoAttivo = new MedicoDiPersona();
				medicoAttivo.setMedico(medico);
				pc.getMedici().remove(medicoAttivo);
				medicoAttivo.setOperation(medico.OP_DELETE);
				System.out.println(medicoAttivo.toString());
				target.add(ListaMediciPanel.this);
			}
		});

	}
	
	public void onScegliMedico(AjaxRequestTarget target, Medico medico) {

	}

}
