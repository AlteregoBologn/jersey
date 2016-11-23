package web.c;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.ComponentTag;

public class CssBeahvior extends AbstractAjaxBehavior {

	public CssBeahvior() {
	}

	@Override
	public void onComponentTag(ComponentTag tag) {
		Component component = getComponent();
		if (component.hasErrorMessage()) {
			tag.append("class", "error", " ");
		}
	}

	@Override
	public void onRequest() {
		// TODO Auto-generated method stub

	}
}
