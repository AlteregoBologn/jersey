package web.comp.medico;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import web.c.BasePanel;
import web.c.Container;

public class WizardMedicoPanel extends BasePanel {

	int attivo = 0;

	List<Panel> child;

	public WizardMedicoPanel(String id, List<Panel> panels) {
		super(id);

		child = panels;

		Form<?> form = new Form("form");

		form.add(new ListView<Panel>("lista", panels) {

			@Override
			protected void populateItem(ListItem<Panel> item) {

				Panel p = item.getModelObject();

				Container c = new Container("panel", p);

				item.add(c);
			}
		});

		add(form);

		AjaxButton scegli = new AjaxButton("scegli") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if (attivo < (panels.size() - 1)) {
					attivo++;
					spegniTutto();
					target.add(WizardMedicoPanel.this);
				}
			}

			@Override
			public boolean isVisible() {

				return (attivo < (panels.size() - 1));
			}
		};

		form.add(scegli);

		AjaxButton conferma = new AjaxButton("conferma") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				target.add(WizardMedicoPanel.this);
				onConferma(target);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {

				super.onError(target, form);
				target.add(WizardMedicoPanel.this);
				target.add(getFeedbackPanel());
			}

			@Override
			public boolean isVisible() {

				return (attivo == (panels.size() - 1));
			}
		};

		form.add(conferma);
	}

	private void spegniTutto() {
		for (Panel p : child) {
			p.setVisible(false);
		}
		child.get(attivo).setVisible(true);
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		spegniTutto();
	}

	public void onConferma(AjaxRequestTarget target) {
		// TODO Auto-generated method stub

	}
}
