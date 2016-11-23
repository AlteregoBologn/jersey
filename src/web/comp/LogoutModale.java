package web.comp;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import model.Esenzione;
import web.comp.persone.PersonaListPanel;

public class LogoutModale extends Panel {

	ModalWindow modal;
	private String result="niente";
	
	public LogoutModale(String id) {
		super(id);

		add(modal = new ModalWindow("modal"));
		modal.setOutputMarkupId(true);
        LogoutPanel logoutPanel=new LogoutPanel(modal.getContentId());
		//modal.setContent(new EmptyPanel(modal.getContentId()));
		modal.setContent(logoutPanel);
		modal.setTitle("Logout");
		//modal.setCookieName("modal-2");
		modal.setInitialHeight(600);
		modal.setInitialWidth(800);
		
		modal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
			@Override
			public boolean onCloseButtonClicked(AjaxRequestTarget target) {
				setResult("B");
				return true;
			}
		});

		add(new AjaxLink<Void>("showModal") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				modal.show(target);
			}
		});
	}
	
	 /**
     * @return the result
     */
    public String getResult()
    {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(String result)
    {
        this.result = result;
    }
}
