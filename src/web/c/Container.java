package web.c;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.IMarkupCacheKeyProvider;
import org.apache.wicket.markup.IMarkupResourceStreamProvider;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;

public class Container extends Panel implements IMarkupResourceStreamProvider, IMarkupCacheKeyProvider {
	Panel child;
	int x;
	int y;
	
	public Container(String id,Panel c) {
		super(id);		
		child = c;
		add(child);
	}
	
	@Override
	public IResourceStream getMarkupResourceStream(MarkupContainer container,
			Class<?> containerClass) {
		
		String markup = "<wicket:panel>";
		markup+="<div  wicket:id=\""+child.getId()+"\"></div>";
		markup+="</wicket:panel>";
		
		StringResourceStream resourceStream = new StringResourceStream(markup);
		return resourceStream;
	}
	
	/**
	* Avoid markup caching for this component
	*/
	@Override
	public String getCacheKey(MarkupContainer arg0, Class<?> arg1) {
		return null;
	}
}

