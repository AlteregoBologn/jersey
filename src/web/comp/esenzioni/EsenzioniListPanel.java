package web.comp.esenzioni;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.EsenzioneSearch;

public class EsenzioniListPanel extends Panel {
	@Autowired
	@SpringBean
	Manager manager;
	EsenzioneSearch es = new EsenzioneSearch();
	
	public EsenzioniListPanel(String id) {
		super(id);

		add(new EsenzioneModale("modaleEsenzione"));
		
		/*
		add(new Grid("esenzioni") {
			@Override
			public long getSize() {
				return manager.countEsenzione(es);
			}

			@Override
			public List getList(long f, long c) {
				es.setPageFrom(f);
				es.setPageTo(f + c);
				return manager.cercaEsenzione(es);
			}

			@Override
			public List getColumns() {
				List<IColumn> columns = new ArrayList();
				columns.add(new PropertyColumn(new Model("Descrizione"), "name.first", "descrizione"));
				// columns.add(new AjaxBuPropertyColumn(new Model("cognome"),"name.last", "cognome"));
				return columns;
			}
		}); */
	} 
}
