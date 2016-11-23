package web.comp.esenzioni;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import model.Persona;
import web.comp.persone.PersonaListPanel;

public class EsenzioneModale extends Panel {

	
	ModalWindow mw;
	private String result="niente";
	
	public EsenzioneModale(String id) {
		super(id);

	
		add(mw = new ModalWindow("modal"));
		mw.setOutputMarkupId(true);
        EsenzioniListPanel listaPanel=new EsenzioniListPanel(mw.getContentId());
		mw.setContent(listaPanel);
		mw.setTitle("Lista delle esenzioni");
		mw.setInitialHeight(600);
		mw.setInitialWidth(800);
		
		mw.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
			@Override
			public boolean onCloseButtonClicked(AjaxRequestTarget target) {
				setResult("B");
				return true;
			}
		});

		mw.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			@Override
			public void onClose(AjaxRequestTarget target) {
				//target.add(result);
			}
		});

		add(new AjaxLink<Void>("showModal") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				mw.show(target);
				//target.add(result);
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
