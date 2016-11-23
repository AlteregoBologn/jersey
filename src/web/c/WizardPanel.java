package web.c;

import java.util.List;

import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.time.Duration;


public class WizardPanel extends BasePanel {

	int attivo = 0;
	
	List<Panel> child;
	
	boolean showButton = true;
	
	

	public WizardPanel(String id, List<Panel> panels) {
		
		super(id);

		setOutputMarkupId(true);
		
		child = panels;

		Form<?> form = new Form("form");

		add(new AbstractAjaxTimerBehavior(Duration.seconds(1))
	    {
	        /**
	         * @see org.apache.wicket.ajax.AbstractAjaxTimerBehavior#onTimer(org.apache.wicket.ajax.AjaxRequestTarget)
	         */
	        protected void onTimer(AjaxRequestTarget target)
	        {
	            target.add(getFeedbackPanel());
	        }
	    });
		form.add(new ListView<Panel>("lista", panels) {
			

			@Override
			protected void populateItem(ListItem<Panel> item) {
				Panel p = item.getModelObject();				
				
				Container c = new Container("panel", p);
				
				item.add(c);				
			}
		});

		add(form);
		
		AjaxButton indietro = new AjaxButton("indietro") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if(attivo > 0) {
					attivo--;
					spegniTutto();	
					target.add(WizardPanel.this);
					onChangePanel(target, attivo);
				}
				
			}
			@Override
			public boolean isVisible() {
				
				return (attivo > 0) && showButton;		
			}
			
			
		};
		//indietro.setDefaultFormProcessing(false);
		form.add(indietro);
		AjaxButton avanti = new AjaxButton("avanti") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				if(attivo < (panels.size()-1)) {
					attivo++;
					spegniTutto();				
					target.add(WizardPanel.this);
					target.add(getFeedbackPanel());
					onChangePanel(target, attivo);
				}			
				
			}
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				
				super.onError(target, form);
				target.add(WizardPanel.this);
				target.add(getFeedbackPanel());
			}
			
			@Override
			public boolean isVisible() {
				
				return (attivo < (panels.size()-1)) && showButton;
			}
			
		};
		form.add(avanti);
		AjaxButton conferma = new AjaxButton("conferma") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				target.add(WizardPanel.this);	
				onConferma(target);
			}
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				
				super.onError(target, form);
				target.add(WizardPanel.this);
				target.add(getFeedbackPanel());
				onChangePanel(target, attivo);
			}
			@Override
			public boolean isVisible() {
				
				//return true;
				return (attivo == (panels.size()-1)) && showButton;
			}
			
			
		};
		form.add(conferma);
		add(form);
	}

	@Override
	protected void onBeforeRender() {		
		super.onBeforeRender();
		spegniTutto();
	}
	
	private void spegniTutto() {
		for(Panel p:child){
			p.setVisible(false);
		}
		child.get(attivo).setVisible(true);
	}
	
	/**
	 * Invocato dal componente sulla pressione del pulsante conferma
	 * @param target
	 */
	public void onConferma(AjaxRequestTarget target) {
	}
	
	public void onChangePanel(AjaxRequestTarget target, Integer attivo) {
	}
	
	public boolean isShowButton() {
		return showButton;
	}

	public void setShowButton(boolean showButton) {
		this.showButton = showButton;
	}

	
}
