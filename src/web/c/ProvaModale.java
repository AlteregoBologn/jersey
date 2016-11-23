package web.c;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import model.Persona;
import web.comp.persone.PersonaListPanel;

public class ProvaModale extends Panel {

	ModalWindow modal;
	private String result="niente";
	
	public ProvaModale(String id) {
		super(id);

		Persona p = new Persona();

		final Label result;
        add(result = new Label("result", new PropertyModel<>(this, "result")));
        result.setOutputMarkupId(true);

		
		add(modal = new ModalWindow("modal"));
		modal.setOutputMarkupId(true);
        PersonaListPanel listaPanel=new PersonaListPanel(modal.getContentId());
		//modal.setContent(new EmptyPanel(modal.getContentId()));
		modal.setContent(listaPanel);
		modal.setTitle("Lista persone registrate");
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

		modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			@Override
			public void onClose(AjaxRequestTarget target) {
				target.add(result);
			}
		});

		add(new AjaxLink<Void>("showModal") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				modal.show(target);
				target.add(result);
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
