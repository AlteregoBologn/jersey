package web.c;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.util.resource.AbstractResourceStreamWriter;

public class MyAllegato extends Panel {

	ModalWindow modal;
	
	//private String result="niente";
	
	public MyAllegato(String id,String title,int w, int h, String linkText) {
		super(id);
		Panel toShow=new FileUploadPanel("allegato"){
			@Override
			public void onClose(AjaxRequestTarget target, FileUpload fileInviato) {
				close(target);
				target.add(MyAllegato.this);
				MyAllegato.this.onUpload(target, fileInviato);
			}
		};
		setOutputMarkupId(true);
		add(modal = new ModalWindow("modal"));
		modal.setOutputMarkupId(true);
		modal.setContent(new Container(modal.getContentId(),toShow));
		//modal.setContent(new EmptyPanel(modal.getContentId()));
		modal.setTitle(title);
		modal.setCookieName("ck"+id);
		modal.setInitialHeight(h);
		modal.setInitialWidth(w);
		
		modal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
			@Override
			public boolean onCloseButtonClicked(AjaxRequestTarget target) {
				return MyAllegato.this.onCloseButtonClicked(target);
			}

			
		});

		modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			@Override
			public void onClose(AjaxRequestTarget target) {
				MyAllegato.this.onClosed(target);				
			}
		});

		Link<Void> streamDownloadLink = new Link<Void>("download") {

		    @Override
		    public void onClick() {

		        AbstractResourceStreamWriter rstream = new AbstractResourceStreamWriter() {

		            @Override
		            public void write(OutputStream output) throws IOException {
		                output.write(getBytesDownload());
		            }
		        };

		        ResourceStreamRequestHandler handler = new ResourceStreamRequestHandler(rstream, getFileName() );        
		        getRequestCycle().scheduleRequestHandlerAfterCurrent(handler);
		    }
		    
		    @Override
		    public boolean isVisible() {
		    	byte []b=getBytesDownload() ;
		    	if( b==null || b.length<=0 ) return false;
		    	return true;
		    }
		};

		add(streamDownloadLink);
		
		AjaxLink link;
		add(link=new AjaxLink<Void>("showModal") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				show(target);
			}
		});
		link.add(new Label("labelUpload",linkText));
	}
	
	public void onUpload(AjaxRequestTarget target, FileUpload fileInviato) {
		
	}
	
	public void show(AjaxRequestTarget target) {
		modal.show(target);
	}
	
	public void close(AjaxRequestTarget target) {
		modal.close(target);
	}
	
	public boolean onCloseButtonClicked(AjaxRequestTarget target) {		
		return true;
	}
	
	public void onClosed(AjaxRequestTarget target) {		
		
	}
	
	public byte[] getBytesDownload() {
		return new byte[]{};
	}
	
	public String getFileName() {
		return "file";
	}
	
	
//	 /**
//     * @return the result
//     */
//    public String getResult()
//    {
//        return result;
//    }
//
//    /**
//     * @param result
//     *            the result to set
//     */
//    public void setResult(String result)
//    {
//        this.result = result;
//    }
//
//	public void setContent(Panel toShow) {
//		modal.setContent(new Container(modal.getContentId(),toShow));
//	}
}
