package web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import web.comp.NavbarPanel;

public class BasePage extends WebPage {
	FeedbackPanel feedback = new FeedbackPanel("feedback");	
	String testo = "Benvenuto ";

	public BasePage() {
		
		add(new NavbarPanel("navbar"));
		Form f=new Form("formA");
		
		if(MySession.get().getUtente() != null) {
			String nome = MySession.get().getUtente().getNome();
			String cognome =  MySession.get().getUtente().getCognome();
			testo += nome + " " + cognome;
		}		
		f.add(new Label("userSpan", testo));
		add(f);
		
		feedback.setOutputMarkupId(true);
		add(feedback);
	}
	
	public FeedbackPanel getFeedback() {
		return feedback;
	}

	public void setFeedback(FeedbackPanel feedback) {
		this.feedback = feedback;
	}
}
