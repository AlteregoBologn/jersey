package web.comp.cambiaPassword;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Persona;
import web.BasePage;

public class ChangePwdPage2 extends BasePage {
	@Autowired
	@SpringBean
	Manager manager;
	
	
	public ChangePwdPage2() {
		getPageParameters().get("email");
		Persona p = new Persona();
		add(new ChangePwdPanel("pwdPanel2", p));
		
		//
		//Persona p=manager.login(username, pwd);
		//MySession.get().setUtente( p );
	}

}
