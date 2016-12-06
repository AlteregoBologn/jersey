package web.comp.medico;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import logic.ManagerExt;
import model.Medico;
import modelExt.MedicoDiPersona;
import modelExt.PersonaCompleta;
import web.c.BasePanel;
import web.comp.esenzioni.ListaEsenzioniPanel;
import web.comp.esenzioni.ModaleListaEsenzioni;

public class ScegliMedicoDiPersonaPanel extends BasePanel {
	@Autowired
	@SpringBean(name = "managerExt")
	ManagerExt managerExt;
	
	MedicoDiPersona medicoAttivo;
	ListaMediciPanel mediciPanel;
	
	public ScegliMedicoDiPersonaPanel(String id, final PersonaCompleta pc) {
		super(id);
		
		medicoAttivo=managerExt.getMedicoAttivo(pc);
		
		
		Form<?> form = new Form("form"){
			@Override
			public boolean isEnabled() {
				
				return false;
			}
		};
		
		form.add(new TextField<>("unid", new PropertyModel<String>(ScegliMedicoDiPersonaPanel.this, "medicoAttivo.medico.unid")));
		form.add(new TextField<>("nome", new PropertyModel<String>(ScegliMedicoDiPersonaPanel.this, "medicoAttivo.medico.nome")));
		form.add(new TextField<>("cognome", new PropertyModel<String>(ScegliMedicoDiPersonaPanel.this, "medicoAttivo.medico.cognome")));
		form.add(new TextField<>("cf", new PropertyModel<String>(ScegliMedicoDiPersonaPanel.this, "medicoAttivo.medico.cf")));		
		form.add(new DateTextField("dataA", new PropertyModel(ScegliMedicoDiPersonaPanel.this, "medicoAttivo.relazione.dataA")));
		form.add(new DateTextField("dataDa", new PropertyModel(ScegliMedicoDiPersonaPanel.this, "medicoAttivo.relazione.dataDa")));

		
//		AjaxButton scegli = new AjaxButton("scegli") {
//			@Override
//			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//				mediciPanel.setVisible(true);
//				target.add(ScegliMedicoDiPersonaPanel.this);
//			}
//
//		};
//		add(scegli);
		
		ListaMediciDiPersonaPanel storicoMedici=new ListaMediciDiPersonaPanel("storicoMedici", pc);
		add(storicoMedici);
		
		add(new ModaleListaMedici("medici", "Lista di tutti i medici",1500,400,new ListaMediciPanel("medici", pc)));
		
//		mediciPanel=new ListaMediciPanel("medici", pc){
//			public void onScegliMedico(AjaxRequestTarget target,Medico m) {
//				
//				mediciPanel.setVisible(false);
//				
//				medicoAttivo=managerExt.scegliMedico(pc,m);
//				//medicoAttivo=managerExt.getMedicoAttivo(pc);
//				//onSalva(target, pc);
//				
//				target.add(ScegliMedicoDiPersonaPanel.this);
//				
//				
//			}
//		};	
//		mediciPanel.setVisible(false);
//		add(mediciPanel);
		add(form);
		
	}
		

	public void onSalva(AjaxRequestTarget target, PersonaCompleta persona) {

	}

}
