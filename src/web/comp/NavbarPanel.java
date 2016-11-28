package web.comp;

import org.apache.wicket.markup.html.link.Link;

import web.Home;
import web.LoginPage;
import web.LogoutPage;
import web.MySession;
import web.c.BasePanel;
import web.comp.cambiaPassword.ChangePwdPage;
import web.comp.persone.ModificaPersonaPage;
import web.comp.registrazione.RegistrazionePage;

public class NavbarPanel extends BasePanel {
	
	public NavbarPanel(String id) {
		super(id);
		
		add(new Link("Home") {
			@Override
			public void onClick() {
				setResponsePage(Home.class);
			}

		});
		
		add(new Link("Registrazione") {
			@Override
			public void onClick() {
				setResponsePage(RegistrazionePage.class);
			}
			@Override
			public boolean isVisible() {
				
				return !utenteLoggato();
			}
		});
		
		add(new Link("Cambia Password") {
			@Override
			public void onClick() {
				setResponsePage(ChangePwdPage.class);
			}
		});
		
		add(new Link("Logout") {
			@Override
			public void onClick() {
				setResponsePage(LogoutPage.class);
			}
			@Override
			public boolean isVisible() {
				
				return utenteLoggato();
			}
		});
		
		add(new Link("Login") {
			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
			@Override
			public boolean isVisible() {
				
				return !utenteLoggato();
			}
		});
		add(new Link("Modifica Persona") {
			@Override
			public void onClick() {
				setResponsePage(ModificaPersonaPage.class);
			}
			@Override
			public boolean isVisible() {
				return true;//utenteLoggato();
			}
		});
	}
	
	public boolean utenteLoggato() {
		return MySession.get().getUtente() == null ? false : true;	
	}
}
