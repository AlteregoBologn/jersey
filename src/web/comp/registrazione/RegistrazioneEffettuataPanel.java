package web.comp.registrazione;

import org.apache.wicket.markup.html.link.Link;

import web.Home;
import web.c.BasePanel;

public class RegistrazioneEffettuataPanel extends BasePanel {
		public RegistrazioneEffettuataPanel(String id) {
			super(id);
			
			add(new Link("Home") {
				@Override
				public void onClick() {
					setResponsePage(Home.class);
				}

			});
		}
}
