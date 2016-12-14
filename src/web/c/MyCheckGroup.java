package web.c;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.core.util.lang.PropertyResolver;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class MyCheckGroup<T> extends Panel {
	
	final public static Integer S = 1;
	final public static Integer N = 0;
	
	T oggetto;
	
	private Boolean value=false;

	public MyCheckGroup(String id,final IModel<Integer> model1,final IModel<Integer> model2,final IModel<Integer> model3, String key, String desc) {
		super(id);
		setOutputMarkupId(true);
		
		PropertyModel<T> dpm = new PropertyModel<T>(MyCheckGroup.this, "oggetto"){
			@Override
			public T getObject() {
				Integer scelta1 = model1.getObject();
				Integer scelta2 = model2.getObject();
				Integer scelta3 = model3.getObject();
				if( scelta1 != null || scelta2!= null || scelta3!= null) {
					List<T> lista = getList();
					for(T t: lista) {
						Integer v = (Integer)PropertyResolver.getValue(key, t);
						if(v.equals(scelta1)||v.equals(scelta2)||v.equals(scelta3)){
							return t;
						}
					}
					if(S.equals(scelta1)||S.equals(scelta2)||S.equals(scelta3)) {
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
		
	}
	public List<T> getList() {
		return new ArrayList<>();
	}
}
	

