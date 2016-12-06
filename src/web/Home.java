package web;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.ManagerExt;
import model.Persona;
import modelExt.PersonaCompleta;
import web.c.ProvaModale;
import web.comp.persone.ModificaPersonaCompletaPanel;

public class Home extends BasePage {
	@Autowired
	@SpringBean
	ManagerExt managerExt;

	public Home() {
		Persona persona = MySession.get().getUtente();

		PersonaCompleta pc = managerExt.loadPersonaCompleta(29);// TODO:
																// persona.getUnid());

		add(new ProvaModale("prova", "Edit Persona",600,400,new ModificaPersonaCompletaPanel("pippo", pc)));
	}
}
