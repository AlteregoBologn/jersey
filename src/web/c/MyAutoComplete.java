package web.c;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.string.Strings;

import model.Citta;
import model.CittaSearch;

public class MyAutoComplete<T> extends Panel {
	final public static String S="S";
	final public static String N="N";
	
	T value;

	public MyAutoComplete(String id, final IModel<T> model, final IModel labelModel, String expression) {
		super(id);
		setOutputMarkupId(true);
		
		PropertyModel acm=new PropertyModel<String>(value,expression){
			@Override
			public void setObject(String str) {
				List<T>  r=getList(str);
				if(!r.isEmpty()) {
					T scelto=r.get(0);
					model.setObject(scelto);
				} else {					
					onNotFound(str);
				}
			}
			@Override
			public String getObject() {
				T a=model.getObject();	
				setObject(typeToString(a));
				a=model.getObject();	
				return typeToString(a);
			}
		};
		final AutoCompleteTextField<String> field2 = new AutoCompleteTextField<String>("field2", acm ) {


			@Override
			protected Iterator<String> getChoices(String input) {
				ArrayList<String> ret=new ArrayList<String>();
				try {
					if (Strings.isEmpty(input)) {
						return ret.iterator();
					}
					
					List<T> l=getList(input);
					if(!l.isEmpty()) {
						value=l.get(0);
						model.setObject(value);
					}
					
					for(T t:l) {
						ret.add(typeToString(t));
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
					
				} finally {
					return ret.iterator();
				}
			}
	
		};
		field2.add(new AjaxEventBehavior("change") {
			protected void onEvent(AjaxRequestTarget target) {
				
				labelModel.setObject(""+value+" "+model.getObject());
				MyAutoComplete.this.onChange(target);
				target.add(MyAutoComplete.this);
			}
		});
		field2.setLabel(labelModel);
		add(new Label("label", labelModel));
		add(field2);
	}

	public void onNotFound(String input) {
	}
	
	public void onChange(AjaxRequestTarget target){
	
	}
	
	public List<T> getList(String input) {
		return new ArrayList();
	}
	
	public String typeToString(T a) {
		if(a==null) return "";
		return "";
	}
	
}
