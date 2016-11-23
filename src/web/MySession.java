package web;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import model.Persona;

public final class MySession extends WebSession {

	private Persona utente;

	public MySession(Request request) {
		super(request);

	}

	public Persona getUtente() {
		
		return utente;
	}

	public void setUtente(Persona utente) {
		this.utente = utente;
	}

	// if you use java >= 1.5 you can make use of covariant return types
	public static MySession get() {
		return (MySession) Session.get();
	}	
}