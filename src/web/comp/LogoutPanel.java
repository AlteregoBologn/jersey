package web.comp;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import web.Home;
import web.MySession;
import web.c.BasePanel;

public class LogoutPanel extends BasePanel {
	@Autowired
	@SpringBean(name = "manager")
	Manager manager;

	public LogoutPanel(String id) {
		super(id);

		Form<?> form = new Form("form");

		AjaxButton button = new AjaxButton("logout") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				MySession.get().setUtente(null);
				target.add(LogoutPanel.this);
				setResponsePage(Home.class);
			}
		};
		form.add(button);
		add(form);

	}

}
