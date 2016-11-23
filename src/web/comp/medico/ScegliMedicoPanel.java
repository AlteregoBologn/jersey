package web.comp.medico;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.MedicoSearch;
import model.Persona;
import web.c.BasePanel;
import web.c.Grid;

public class ScegliMedicoPanel extends BasePanel {
	@Autowired
	@SpringBean
	Manager manager;

	MedicoSearch ms = new MedicoSearch();

	public ScegliMedicoPanel(String id, Persona p) {
		super(id);

		add(new Grid("medici") {
			@Override
			public long getSize() {

				return manager.countMedico(ms);
			}

			@Override
			public List getList(long f, long c) {
				ms.setPageFrom(f);
				ms.setPageTo(f + c);
				return manager.cercaMedico(ms);
			}

			@Override
			public List getColumns() {
				List<IColumn> columns = new ArrayList();
				columns.add(new PropertyColumn(new Model("Nome"), "name.first", "nome"));
				columns.add(new PropertyColumn(new Model("Cognome"), "name.last", "cognome"));
				columns.add(new PropertyColumn(new Model("Codice Fiscale"), "name.last", "cf"));
				// columns.add(new AjaxBuPropertyColumn(new Model("cognome"),
				// "name.last", "cognome"));

				return columns;
			}
		});
	}

}
