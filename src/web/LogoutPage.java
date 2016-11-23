package web;

import web.comp.LogoutPanel;

public class LogoutPage extends BasePage {
	
	
	public LogoutPage(){
		add(new LogoutPanel("logoutPanel"));

	}
	
	@Override
	public boolean isVisible() {
		return MySession.get().getUtente()!=null;
	}
}
