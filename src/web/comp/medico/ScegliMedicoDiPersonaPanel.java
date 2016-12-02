package web.comp.medico;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.ManagerExt;
import model.Medico;
import model.Rel_Persona_Medico;
import modelExt.MedicoDiPersona;
import modelExt.PersonaCompleta;
import web.c.BasePanel;

public class ScegliMedicoDiPersonaPanel extends BasePanel {
	@Autowired
	@SpringBean(name = "managerExt")
	ManagerExt managerExt;
	
	MedicoDiPersona medicoAttivo;
	ListaMediciPanel mediciPanel;
	
	public ScegliMedicoDiPersonaPanel(String id, final PersonaCompleta pc) {
		super(id);
		
		Form<?> form = new Form("form");
		
		medicoAttivo = pc.getMedicoAttivo();
	
		form.add(new TextField<>("nome", new PropertyModel<String>(ScegliMedicoDiPersonaPanel.this, "medicoAttivo.medico.nome")));
		//form.add(new TextField<>("cognome", new PropertyModel<String>(ScegliMedicoDiPersonaPanel.this, "medicoAttivo.cognome")));
		//form.add(new TextField<>("cf", new PropertyModel<String>(ScegliMedicoDiPersonaPanel.this, "medicoAttivo.medico.cf")));		
		//form.add(new TextField<>("dataA", new PropertyModel<String>(ScegliMedicoDiPersonaPanel.this, "medicoAttivo.relazione.dataA")));
		//form.add(new TextField<>("dataDa", new PropertyModel<String>(ScegliMedicoDiPersonaPanel.this, "medicoAttivo.relazione.dataDa")));

		AjaxButton scegli = new AjaxButton("scegli") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				//managerExt.salvaMedicoDiPersona();
				target.add(ScegliMedicoDiPersonaPanel.this);
				onSalva(target, pc);
			}

		};
		form.add(scegli);

		mediciPanel=new ListaMediciPanel("medici"){
			public void onScegliMedico(AjaxRequestTarget target,Medico m) {
				medicoAttivo.setMedico(m);
				Rel_Persona_Medico relazione=new Rel_Persona_Medico();
				relazione.setIdmedico(m.getUnid());
				medicoAttivo.setRelazione(relazione);
				target.add(ScegliMedicoDiPersonaPanel.this);
			}
		};
		add(mediciPanel);
		
		add(form);
	}

	public void onSalva(AjaxRequestTarget target, PersonaCompleta persona) {

	}

}
