package web.comp.medico;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import web.c.Container;
import web.comp.esenzioni.ModaleListaEsenzioni;

public class ModaleListaMedici extends Panel {
	
	ModalWindow modal;
	
	public ModaleListaMedici(String id, String title, int w, int h, Panel toShow){
		super(id);
		setOutputMarkupId(true);
		add(modal = new ModalWindow("modal"));
		modal.setOutputMarkupId(true);
		modal.setTitle(title);
		modal.setContent(new Container(modal.getContentId(), toShow));
		modal.setCookieName("ck" + id);
		modal.setInitialHeight(w);
		modal.setInitialWidth(h);

		modal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
			@Override
			public boolean onCloseButtonClicked(AjaxRequestTarget target) {
				return ModaleListaMedici.this.onCloseButtonClicked(target);
			}

		});

		modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			@Override
			public void onClose(AjaxRequestTarget target) {
				ModaleListaMedici.this.onClosed(target);
			}
		});
		
		AjaxButton scegli = new AjaxButton("scegli") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				show(target);
			}

		};
		add(scegli);

//		add(new AjaxLink<Void>("showModal") {
//			@Override
//			public void onClick(AjaxRequestTarget target) {
//				show(target);
//			}
//		});

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

	

}
