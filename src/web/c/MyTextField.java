package web.c;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class MyTextField extends Panel  {
	
	public MyTextField(String id,IModel model,IModel labelModel){
		super(id);
		add(new Label("label",labelModel));
		TextField<String> field = new TextField<String>("field",model){
			@Override
			public boolean isRequired() {
				// TODO Auto-generated method stub
				return MyTextField.this.isRequired();
			}
		};
		add(field);
	}
	
	public boolean isRequired(){
		return false;
	}
}
