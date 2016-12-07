package web.c;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.core.util.lang.PropertyResolver;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.string.Strings;

import model.Citta;
import model.CittaSearch;
import model.Decodifica;
import tari.web.comp.panels.EditDichiarazionePanel;

public class MyDropDown<T> extends Panel {
	
	T value;

	public MyDropDown(String id, final IModel<String> ddm, String key,String desc) {
		super(id);
		setOutputMarkupId(true);
		

		//PropertyModel<String> ddm=new PropertyModel<String>(MyDropDown.this, "dichiarazione.dichiarazioneImmobile.immobile.qualita");
		
		PropertyModel<T> dpm=new PropertyModel<T>(MyDropDown.this, "value"){
			@Override
			public void setObject(T object) {
				String v=(String) PropertyResolver.getValue(key, object);
				ddm.setObject(v);
			}
			@Override
			public T getObject() {
				String letto=ddm.getObject();
				if( letto!=null ) {
					List<T> a=getList();
					for(T t:a) {
						String v=(String) PropertyResolver.getValue(key, t);
						if(v.equals(letto)){
							return t;
						}
					}
				}
				return null;
			}
		};
		final DropDownChoice field2;
		add(field2=new DropDownChoice<T>("field2", dpm , getList(),  new ChoiceRenderer<T>(desc, key) ) {
			@Override
			public List<? extends T> getChoices() {
				return  getList() ;
			}
		});
		field2.add(new AjaxEventBehavior("change") {
			protected void onEvent(AjaxRequestTarget target) {
				
				MyDropDown.this.onChange(target);
			}
		});	
		
	}

	public void onChange(AjaxRequestTarget target){
		
	}
	
	public List<T> getList() {
		return new ArrayList();
	}
	
	
}
