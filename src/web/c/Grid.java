package web.c;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import model.E;

public class Grid<T extends E > extends Panel {
	SortParam<T> sort;

	boolean showNuovo=true;
	boolean showEdit=true;
	boolean showDelete=true;
	boolean showSelect=true;
	boolean showOperation=true;
	
	public <S> Grid(String id) {
		this(id,true,true,true,true, true);		
	}
	
	@SuppressWarnings("rawtypes")
	public <S> Grid(String id,boolean showNuovo,boolean showSelect,boolean showEdit,boolean showDelete, boolean showOperation) {
		super(id);
		this.showEdit=showEdit;
		this.showNuovo=showNuovo;
		this.showDelete=showDelete;
		this.showSelect=showSelect;
		this.showOperation = showOperation;
		
		final SortableDataProvider dp = new SortableDataProvider<T, S>() {

			@Override
			public Iterator<? extends T> iterator(long first, long count) {
				// long to int !!!
				return (Grid.this.getList((int)first, (int)count)).iterator();
			}

			@Override
			public long size() {

				return Grid.this.getSize();
			}

			@Override
			public IModel<T> model(T object) {

				return new Model((Serializable) object);
			}
		};
		
		add(new AjaxButton("nuovo"){
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				onNew(target);
			}		
			@Override
			public boolean isVisible() {
				// 
				return showNuovo;
			}
		}); 
		
		DefaultDataTable table = new DefaultDataTable("datatable", Grid.this._getColumns(), dp, getMaxRows());
		table.addBottomToolbar(new NavigationToolbar(table));
		//table.addTopToolbar(new HeadersToolbar(table, dp));

		add(table);
	}

	public List<T> getList(int f, int c) {
		return new ArrayList();
	}

	public long getSize() {
		return 1000L;
	}

	public int getMaxRows() {
		return 10;
	}

	final private List<IColumn> _getColumns() {
		List<IColumn> columns=new ArrayList();
		if(showOperation){
			columns.add(new PropertyColumn<T,String>(new Model("operation"), "operation"));
		}		
		columns.addAll( getColumns() );
		/*columns.add(new PropertyColumn(new Model("First Name"), "name.first", "name.first"));
		columns.add(new PropertyColumn(new Model("Last Name"), "name.last", "name.last"));*/
		
	if(showSelect)	{
		columns.add(new AbstractColumn<T, String>(new Model("Select")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void populateItem(final Item<ICellPopulator<T>> item, final String componentId, final IModel<T> rowModel)
			{
				item.add(new GridAjaxButtonPanel<T>(componentId, "Select", rowModel.getObject()){
					@Override
					public void onClick(AjaxRequestTarget target, T o) {	
						onSelect(target,o);
					}
				});		
			}			
		});
	}
	if(showEdit){
		columns.add(new AbstractColumn<T, String>(new Model<>("Edit")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void populateItem(final Item<ICellPopulator<T>> item, final String componentId, final IModel<T> rowModel)
			{
				item.add(new GridAjaxButtonPanel<T>(componentId, "Edit", rowModel.getObject()){
					@Override
					public void onClick(AjaxRequestTarget target, T o) {						
						onEdit(target,o);
					}
				});				
			}
		});
	}
	if(showDelete) {
		columns.add(new AbstractColumn<T, String>(new Model("Del")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void populateItem(final Item<ICellPopulator<T>> item, final String componentId, final IModel<T> rowModel)
			{
				item.add(new GridAjaxButtonPanel<T>(componentId,  "Delete", rowModel.getObject()){
					@Override
					public void onClick(AjaxRequestTarget target, T o) {
						
						onDelete(target,rowModel.getObject());
					}
				});						
			}
		});
	}
		return columns;

	}
	
	public List<IColumn> getColumns() {
		List<IColumn> columns = new ArrayList();
		/*columns.add(new PropertyColumn(new Model("First Name"), "name.first", "name.first"));
		columns.add(new PropertyColumn(new Model("Last Name"), "name.last", "name.last"));*/
		return columns;

	}
	
	public void onEdit(AjaxRequestTarget target, T object) {
		
	}
	public void onDelete(AjaxRequestTarget target, T object) {
		
		
	}
	public void onNew(AjaxRequestTarget target) {
		
		
	}
	public void onSelect(AjaxRequestTarget target, T object) {
		
		
	}
}
