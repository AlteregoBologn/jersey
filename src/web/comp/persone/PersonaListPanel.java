package web.comp.persone;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Persona;
import model.PersonaSearch;
import web.c.Grid;

public class PersonaListPanel extends Panel {
	@Autowired
	@SpringBean(name="manager")
	Manager manager;
	PersonaSearch search = new PersonaSearch();

	public PersonaListPanel(String id) {
		super(id);

		setOutputMarkupId(true);

		Form<?> form = new Form("form");

		PropertyModel p = new PropertyModel(this, "manager") {
			public Object getObject() {
				return manager.cercaPersone(new PersonaSearch());
			};
		};

		form.add(new ListView<Persona>("lista", p) {

			@Override
			protected void populateItem(ListItem<Persona> item) {
				Persona p = item.getModelObject();
				item.add(new Label("nome", new PropertyModel(p, "nome")));
				item.add(new Label("cognome", new PropertyModel(p, "cognome")));
				AjaxButton button = new AjaxButton("edit") {
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
						onEdit(p, target);
					}

				};
				item.add(button);
				AjaxButton del = new AjaxButton("del") {
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
						onDel(p, target);
					}

				};
				item.add(del);
			}
		});

		form.add(new TextField<String>("cognome", new PropertyModel<String>(PersonaListPanel.this, "search.cognomeLike")));
		AjaxButton cerca = new AjaxButton("cerca") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

				target.add(PersonaListPanel.this);
			}

		};
		form.add(cerca);

		form.add(new Grid("persone") {
			@Override
			public long getSize() {
				return manager.countPersone(search);
			}

			@Override
			public List getList(int f, int c) {
				search.setPageFrom((long)f);
				search.setPageTo((long)f + (long)c);
				return manager.cercaPersone(search);
			}

			@Override
			public List getColumns() {
				List<IColumn> columns = new ArrayList();
				columns.add(new PropertyColumn(new Model("Nome"), "name.first", "nome"));
				columns.add(new PropertyColumn(new Model("Cognome"), "name.last", "cognome"));
				
				// columns.add(new AjaxBuPropertyColumn(new Model("cognome"),
				// "name.last", "cognome"));
				
				return columns;
			}
			
			/*
			@Override
			public void onEdit(AjaxRequestTarget target, Object object) {
				
			}
			
			@Override
			public void onDelete(AjaxRequestTarget target, Object object) {
				super.onDelete(target, object);
			}
			*/
		});

		add(form);
	}

	public void onEdit(Persona persona, AjaxRequestTarget target) {
	}

	public void onDel(Persona persona, AjaxRequestTarget target) {
	}
}
