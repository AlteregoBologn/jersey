package web.c;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.core.util.lang.PropertyResolver;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class MyRadioGroup<T> extends Panel {
	
	final public static Integer S = 1;
	final public static Integer N = 0;
	
	T oggetto;
	
	private Boolean value=false;

	public MyRadioGroup(String id, final IModel<Integer> model1,final IModel<Integer> model2,final IModel<Integer> model3, String key, String desc) {
		super(id);
		setOutputMarkupId(true);
		
		PropertyModel<T> dpm = new PropertyModel<T>(MyRadioGroup.this, "oggetto"){
			@Override
			public T getObject() {
				Integer selezionato1 = model1.getObject();
				Integer selezionato2 = model2.getObject();
				Integer selezionato3 = model3.getObject();
				if( selezionato1 != null || selezionato2!= null || selezionato3!= null) {
					List<T> a = getList();
					for(T t:a) {
						Integer v = (Integer)PropertyResolver.getValue(key, t);
						if(v.equals(selezionato1)||v.equals(selezionato2)||v.equals(selezionato3)){
							return t;
						}
					}
					if(S.equals(selezionato1)||S.equals(selezionato2)||S.equals(selezionato3)) {
						value = true;
					} else {
						value = false;
					}
					
				}
				return super.getObject();
			}
			
			@Override
			public void setObject(T object) {
				Integer v = (Integer)PropertyResolver.getValue(key, object);
				model1.setObject(v);
				model2.setObject(v);
				model3.setObject(v);
			}			
		};

		
		final RadioChoice<T> field;
		add(field=new RadioChoice<T>("field", dpm , getList(),  new ChoiceRenderer<T>(desc, key) ) {
			@Override
			public List<? extends T> getChoices() {
				return  getList() ;
			}
		});
		field.add(new AjaxEventBehavior("click") {
			protected void onEvent(AjaxRequestTarget target) {
				
				value = !value;
				if(value){
					if(S.equals(model1.getObject())){
					}
					else if(S.equals(model2.getObject())){

					}else if(S.equals(model3.getObject())){

					}
					
				}
				
				
				else model1.setObject(N);
				target.add(MyRadioGroup.this);
				System.out.println(model1);				
				onClick(target);
				
			}		
		});	
		field.setRequired(true);

	}

	public void onClick(AjaxRequestTarget target) {
	}
	
	public List<T> getList() {
		return new ArrayList<>();
	}
}
