package web;

import org.apache.wicket.markup.html.panel.Panel;

import web.c.ProvaModale;
import web.comp.persone.PersonaListPanel;

public class Home extends BasePage {
	
	
	public Home(){
		
			add( new ProvaModale("prova") );
	}
}
