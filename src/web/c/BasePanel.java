package web.c;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class BasePanel  extends Panel {
	
	public BasePanel(String id) {
		super(id);		
		setOutputMarkupId(true);	
	}
	
	public FeedbackPanel getFeedbackPanel() {
		return (FeedbackPanel) getPage().get("feedback");
	}
}
