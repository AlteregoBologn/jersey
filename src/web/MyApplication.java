package web;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import web.comp.cambiaPassword.ChangePwdPage;
import web.comp.cambiaPassword.ChangePwdPage2;

public class MyApplication extends WebApplication {

	/**
	 * Constructor.
	 */
	public MyApplication() {
		System.out.println("MyApplication On: http://localhost:8081/jersey/w/");
	}
	
	@Override
	protected void init() {

		super.init();
		mountPage("/changePwd", ChangePwdPage.class);
		mountPage("/echo", Echo.class);
		mountPage("/changePwd2", ChangePwdPage2.class);
		
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		//addComponentInstantiationListener(new SpringComponentInjector(this));

	}		
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends Page> getHomePage() {
		return Home.class;
	}

	@Override
    public final Session newSession(Request request, Response response) {
        return new MySession(request);
    }
}
