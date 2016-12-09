package web.c;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class MyCheckBox extends Panel {
	final public static Integer S = 1;
	final public static Integer N = 0;
	
	private Boolean value=false;

	public MyCheckBox(String id, final IModel<Integer> model, final IModel labelModel) {
		super(id);
		setOutputMarkupId(true);
		
		PropertyModel pm=new PropertyModel<Boolean>(MyCheckBox.this,"value"){
			@Override
			public Boolean getObject() {
				Integer valueS = model.getObject();
				if(S.equals(valueS)) {
					value=true;
				} else {
					value=false;
				}
				return super.getObject();
			}
			@Override
			public void setObject(Boolean object) {
				if(value) model.setObject(S);
				else model.setObject(N);
			}
		};
		
		CheckBox field = new CheckBox("field", pm) ;
		field.add(new AjaxEventBehavior("click") {
			protected void onEvent(AjaxRequestTarget target) {
				value=!value;
				if(value) model.setObject(S);
				else model.setObject(N);
				onClick(target);
				
				//labelModel.setObject(""+value+" "+model.getObject());
				
				target.add(MyCheckBox.this);
			}
		});
		field.setLabel(labelModel);
		add(new Label("label", labelModel));
		
		add(field);
	}

	public void onClick(AjaxRequestTarget target) {
	}
}

