package web;

import org.apache.wicket.spring.injection.annot.SpringBean;

import logic.ManagerExt;
import model.Persona;
import modelExt.PersonaCompleta;

public class Home extends BasePage {
	@SpringBean
	ManagerExt managerExt;

	public Home() {
		
		Persona persona = MySession.get().getUtente();

		//PersonaCompleta pc = managerExt.loadPersonaCompleta(411);// TODO persona.getUnid());
		//add(new ProvaModale("prova", "Edit Persona",600,400,new ModificaPersonaCompletaPanel("pippo", pc)));
	}
}
