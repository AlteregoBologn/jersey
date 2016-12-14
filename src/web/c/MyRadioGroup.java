package web.c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.core.util.lang.PropertyResolver;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class MyRadioGroup extends Panel {
	
	final public static Integer S = 1;
	final public static Integer N = 0;
	
	public MyRadioGroup(String id, final IModel model,Map<String, String> checks) {
		super(id);
		setOutputMarkupId(true);
		
		List<String> list = new ArrayList<String>(checks.keySet());
		
		add(new ListView<String>("lista", list ) {
			@Override
			protected void populateItem(ListItem<String> item) {
				String expression=item.getModelObject();
				String labelForExpression=checks.get(expression);
		   		MyCheckBox check = new MyCheckBox("check",
						new PropertyModel(model,expression), 
						new Model(labelForExpression)){
		   			@Override
		   			public void onChange(AjaxRequestTarget target, boolean value) {
		   				// sbianca tutti
		   				if( isRadio() ) {
			   				for(String expr:list) {
			   					PropertyResolver.setValue(expr, model.getObject(),N,null);
			   				}
		   				} else {
		   					
		   				}
		   				// setta solo il cliccato
		   				if( value ) {
		   					PropertyResolver.setValue(expression, model.getObject(),S,null);
		   				} else {
		   					PropertyResolver.setValue(expression, model.getObject(),N,null);
		   				}
		   				
		   				// ridisegna componente
		   				target.add(MyRadioGroup.this);
		   				// propaga evento
		   				MyRadioGroup.this.onChange(target);
		   			}
		   		};
		   		
		   		item.add(check);
			}	
		});
		

	}
	
	public boolean isRadio() {
		return true;
	}
	
	public void onChange(AjaxRequestTarget target) {
	}
	

}
