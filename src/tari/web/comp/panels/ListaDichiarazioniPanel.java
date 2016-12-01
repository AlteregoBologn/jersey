package tari.web.comp.panels;

import java.util.Date;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tari.logic.TariManagerExt;
import tari.modelExt.PersonaTariCompleta;
import util.JACK;
import web.c.BasePanel;
	
public class ListaDichiarazioniPanel extends BasePanel {

	@SpringBean
	TariManagerExt tariManagerExt;
	
	public ListaDichiarazioniPanel(String id) throws Exception {
		super(id);
		
		Form form = new Form("form");
		
		PersonaTariCompleta personaCompleta = tariManagerExt.loadPersonaTariCompleta(244);// TODO: persona.getUnid());
		
		form.add(new Label("personaCompleta", JACK.toJSON(personaCompleta)));
		//form.add(new Label("dichiarazioni", personaCompleta.getDichiarazioni().get(0)));
		//form.add(new Label("dichiarazioniImmobile", personaCompleta.getDichiarazioniImmobili().get(0)));
		add(form);
	}
}
