package web.comp;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.Manager;
import model.Medico;
import model.MedicoSearch;
import model.Persona;
import model.Rel_Persona_Medico;
import model.Rel_Persona_MedicoSearch;
import web.MySession;
import web.c.BasePanel;

public class VisualizzaMedicoPanel extends BasePanel {
	@Autowired
	@SpringBean(name="manager")
	Manager manager;
	
	Persona persona = new Persona();
	MedicoSearch medicoSearch = new MedicoSearch();
	Medico medico = new Medico();
	Rel_Persona_MedicoSearch s = new Rel_Persona_MedicoSearch();
	
	public VisualizzaMedicoPanel (String id) {
		
		super(id);
		
		persona = MySession.get().getUtente();
		
		s.setIdpersona(persona.getUnid());
		
		List<Rel_Persona_Medico> relPersonaMedico = manager.cercaPersonaMedico(s);
		
		medicoSearch.setUnid(relPersonaMedico.get(0).getIdmedico());
		
		List<Medico> ret = manager.cercaMedico(medicoSearch);
		
		medico = ret.get(0);		
		
		add(new Label("nome", new PropertyModel<String>(VisualizzaMedicoPanel.this, "medico.nome")));
		
		add(new Label("cognome", new PropertyModel<String>(VisualizzaMedicoPanel.this, "medico.cognome")));
		
		add(new Label("codFisc", new PropertyModel<String>(VisualizzaMedicoPanel.this, "medico.cf"))); 
		
		add(new LogoutModale("Modale"));


	}

}
