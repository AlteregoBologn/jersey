package web.c;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import model.Persona;
import web.comp.persone.PersonaListPanel;

public class ProvaModale extends Panel {

	ModalWindow modal;
	//private String result="niente";
	
	public ProvaModale(String id,String title,int w, int h, Panel toShow) {
		super(id);
		setOutputMarkupId(true);
		add(modal = new ModalWindow("modal"));
		modal.setOutputMarkupId(true);
		modal.setContent(new Container(modal.getContentId(),toShow));
		//modal.setContent(new EmptyPanel(modal.getContentId()));
		modal.setTitle(title);
		modal.setCookieName("ck"+id);
		modal.setInitialHeight(w);
		modal.setInitialWidth(h);
		
		modal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
			@Override
			public boolean onCloseButtonClicked(AjaxRequestTarget target) {
				return ProvaModale.this.onCloseButtonClicked(target);
			}
		});

		modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			@Override
			public void onClose(AjaxRequestTarget target) {
				ProvaModale.this.onClosed(target);				
			}
		});

//		add(new AjaxLink<Void>("showModal") {
//			@Override
//			public void onClick(AjaxRequestTarget target) {
//				show(target);
//			}
//		});
		
		add(new AjaxButton("scegli"){
			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form) {
				show(target);
			}
		});
		
//		AjaxButton scegli = new AjaxButton("scegli") {
//			@Override
//			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//				show(target);
//			}
//
//		};
//		add(scegli);
		
	}
	
	public void show(AjaxRequestTarget target) {
		modal.show(target);
	}
	
	public void close(AjaxRequestTarget target) {
		modal.close(target);
	}
	
	public boolean onCloseButtonClicked(AjaxRequestTarget target) {		
		return true;
	}
	
	public void onClosed(AjaxRequestTarget target) {		
		
	}
	
//	 /**
//     * @return the result
//     */
//    public String getResult()
//    {
//        return result;
//    }
//
//    /**
//     * @param result the result to set
//     *            
//     */
//    public void setResult(String result)
//    {
//        this.result = result;
//    }
//
//	public void setContent(Panel toShow) {
//		modal.setContent(new Container(modal.getContentId(),toShow));
//	}
}
