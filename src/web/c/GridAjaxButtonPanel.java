package web.c;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class GridAjaxButtonPanel<T> extends Panel {
	
	public GridAjaxButtonPanel(String id, String label, T o){
		super(id);
		AjaxButton bottone=new AjaxButton("bottone") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				onClick(target,o);
			}

		};		
		//bottone.setLabel(new Model(label));
		add(bottone);
	}
	
	public void onClick(AjaxRequestTarget target, T o) {
	}
}
