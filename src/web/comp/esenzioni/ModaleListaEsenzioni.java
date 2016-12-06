package web.comp.esenzioni;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import web.c.Container;

public class ModaleListaEsenzioni extends Panel {

	ModalWindow modal;

	public ModaleListaEsenzioni(String id, String title, int w, int h, Panel toShow) {
		super(id);
		setOutputMarkupId(true);
		add(modal = new ModalWindow("modal"));
		modal.setOutputMarkupId(true);
		modal.setContent(new Container(modal.getContentId(), toShow));
		modal.setTitle(title);
		modal.setCookieName("ck" + id);
		modal.setInitialHeight(w);
		modal.setInitialWidth(h);

		modal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
			@Override
			public boolean onCloseButtonClicked(AjaxRequestTarget target) {
				return ModaleListaEsenzioni.this.onCloseButtonClicked(target);
			}
		});

		modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			@Override
			public void onClose(AjaxRequestTarget target) {
				ModaleListaEsenzioni.this.onClosed(target);
			}
		});
		
		AjaxButton scegli = new AjaxButton("scegli") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				show(target);
			}
		};
		add(scegli);
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
