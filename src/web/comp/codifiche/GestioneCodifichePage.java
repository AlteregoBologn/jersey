package web.comp.codifiche;

import org.apache.wicket.spring.injection.annot.SpringBean;

import logic.ManagerExt;
import web.BasePage;

public class GestioneCodifichePage extends BasePage {
	@SpringBean
	ManagerExt managerExt;
	
	public GestioneCodifichePage(){
		
	}

}
