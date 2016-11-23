package jersey;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import jersey.dto.MedicoDTO;
import jersey.dto.ResponseDTO;
import model.Medico;
import model.Persona;


@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:context.xml"})
public class JerseyTest
{
	Logger log;
    @Autowired
    private Service service;

    public JerseyTest()
    {
        log = Logger.getAnonymousLogger();
    }

    @Test
    public void testCall()
    {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8080/jersey/rest/service/test");
        ((com.sun.jersey.api.client.WebResource.Builder)webResource.type("application/json").accept(new String[] {
            "application/json"})).post("ciao");
    }

    @Test
    public void registraPersona()
    {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8081/jersey/rest/service/registraPersona");
        Persona p = new Persona();
        p.setNome("nome");
        p.setCognome("cognome");
        p.setEmail("a");
        p.setPassword("b");
        p.setCf("cf");
        ((com.sun.jersey.api.client.WebResource.Builder)webResource.type("application/json").accept(new String[] {
            "application/json"})).post(p);
        if(p.getUnid() == null)
            throw new RuntimeException("non va");
        else
            return;
    }
    
    @Test
    public void registraMedico()
    {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8081/jersey/rest/service/insertMedico");
        Medico p = new Medico();
        p.setNome("nome");
        p.setCognome("cognome");
        p.setEmail("a");
        p.setPassword("b");
        p.setCf("cf2");
        ResponseDTO ret=((com.sun.jersey.api.client.WebResource.Builder)webResource.type("application/json").accept(new String[] {
            "application/json"})).post(ResponseDTO.class,p);
        log.info(""+ret);
        if(ret.getCountTotal()==0)
            throw new RuntimeException("non va");
        else
            return;
    }
    @Test
    public void testTrovaMedico()
    {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8081/jersey/rest/service/trovaMedico/pippo");
        MedicoDTO ret=((com.sun.jersey.api.client.WebResource.Builder)webResource.type("application/json").accept(new String[] {
            "application/json"})).get(MedicoDTO.class);
        log.info(""+ret);
    }
    
    @Test
    public void testCancellaMedico()
    {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8081/jersey/rest/service/trovaMedico/pippo");
        ResponseDTO ret=((com.sun.jersey.api.client.WebResource.Builder)webResource.type("application/json").accept(new String[] {
            "application/json"})).get(ResponseDTO.class);
        log.info(""+ret);
    }
    
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
