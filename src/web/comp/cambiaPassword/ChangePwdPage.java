package web.comp.cambiaPassword;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Persona;
import web.BasePage;

public class ChangePwdPage extends BasePage {
	@Autowired
	@SpringBean(name="manager")
	Manager manager;
	
	
	public ChangePwdPage() {
		getPageParameters().get("email");
		Persona p = new Persona();
		add(new ChangePwdPanel("pwdPanel", p));
		
		//
		//Persona p=manager.login(username, pwd);
		//MySession.get().setUtente( p );
	}

}
