package web;

import model.Persona;
import web.comp.VisualizzaMedicoPanel;

public class VisualizzaMedicoPage extends BasePage {
	
	public VisualizzaMedicoPage(){

		add(new VisualizzaMedicoPanel("medicoPanel"));
	}


}
