package tari.web.comp.panels;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import tari.logic.TariManagerExt;
import tari.modelExt.DichiarazioneDiImmobile;
import tari.modelExt.DichiarazioneDiPersonaTari;
import tari.modelExt.PersonaTariCompleta;
import web.c.BasePanel;
	
public class ListaDichiarazioniPanel extends BasePanel {

	@SpringBean
	TariManagerExt tariManagerExt;
	
	public ListaDichiarazioniPanel(String id) {
		super(id);
		Form form = new Form("form");
		
		PersonaTariCompleta personaCompleta = tariManagerExt.loadPersonaTariCompleta(29);// TODO: persona.getUnid());
		
		form.add(new Label("personaCompleta", personaCompleta.toString()));
		//form.add(new Label("dichiarazioni", personaCompleta.getDichiarazioni().get(0)));
		//form.add(new Label("dichiarazioniImmobile", personaCompleta.getDichiarazioniImmobili().get(0)));
		
		add(form);
	}
}
