package web.c;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class MyCheckBoxGroup extends Panel {
	
	final public static Integer S = 1;
	final public static Integer N = 0;

	public MyCheckBoxGroup(String id, Integer size, final IModel<Integer> model, final IModel labelModel) {
		super(id);
		setOutputMarkupId(true);
		Integer i;
		List<MyCheckBox> checkBox = new ArrayList<MyCheckBox>();
		for(i=0; i<size; i++){
			//add(new MyCheckBox(id, model, labelModel)
			checkBox.add(new MyCheckBox(id, model, labelModel){
				@Override
				public void onClick(AjaxRequestTarget target, Boolean value, IModel<Integer> model) {

					onThisClick(target, value, model);
				}
			});
		}
		
	}
	public void onThisClick(AjaxRequestTarget target, Boolean value, IModel<Integer> model){
		value = !value;
		if(S.equals(model.getObject())){
			model.setObject(N);
		}
		else model.setObject(S);
	}
		
}
