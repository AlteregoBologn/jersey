package web.c;

import java.io.File;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.lang.Bytes;

public class FileUploadPanel extends BasePanel {

	private FileUploadField fileUpload;
	private String UPLOAD_FOLDER = "C:/Users/TEMP.ASI3170GZ/Desktop/temp/";
	String message="ok";

	public FileUploadPanel(String id) {
		super(id);

		Form<?> form = new Form<Void>("formUpload") {
			
		};

		form.setMultiPart(true);

		form.setMaxSize(Bytes.kilobytes(100));

		form.add( new Label("messaggio",new PropertyModel<>(FileUploadPanel.this, "message")));
		
		form.add(fileUpload = new FileUploadField("fileUpload"));

		add(form);

		form.add(new AjaxSubmitLink("salva") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				UPLOAD_FOLDER = "C:/Users/TEMP.ASI3170GZ/Desktop/temp/";
				 
				final FileUpload uploadedFile = fileUpload.getFileUpload();
				if (uploadedFile != null) {

					// write to a new file
					File newFile = new File(UPLOAD_FOLDER + uploadedFile.getClientFileName());

					if (newFile.exists()) {
						newFile.delete();
					}

					try {
						newFile.createNewFile();
						uploadedFile.writeTo(newFile);
						message+="ok"+ uploadedFile.getClientFileName();
						
						info("saved file: " + uploadedFile.getClientFileName());
						
						onClose(target,uploadedFile);
						
					} catch (Exception e) {
						message+=e.getMessage()+ uploadedFile.getClientFileName();
						
					}
				}

				target.add(FileUploadPanel.this);
				
			}
		});

	}

	public void onClose(AjaxRequestTarget target,FileUpload fileInviato){
		
	}
}
