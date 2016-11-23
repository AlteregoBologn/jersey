package web;

import model.Persona;
import web.comp.LoginPanel;

public class LoginPage extends BasePage {
	
	public LoginPage(){
		Persona p = new Persona();
		add(new LoginPanel("loginPanel", p));	
	}
	
	@Override
	public boolean isVisible() {
		return MySession.get().getUtente()==null;
	}

}
