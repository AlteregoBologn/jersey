package jersey;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jersey.dto.MedicoDTO;
import jersey.dto.PersonaDTO;
import jersey.dto.ResponseDTO;
import logic.Manager;
import model.Medico;
import model.MedicoSearch;
import model.Persona;
import model.PersonaSearch;

@Component
@Path(value = "/service")
public class Service {
	@Autowired
	public Manager manager;

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Service() {
		System.out.println("service On:");
	}

	@POST
	@Path(value = "/test")
	public Response test() {
		String result = manager.save();
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path(value = "/registraPersona")
	public Response registraPersona(Persona p) {
		manager.registraPersona(p);
		return Response.status(200).entity(p).build();
	}

	@GET
	@Path(value = "trovaPersona/{cf}")
	public Response trovaPersona(@PathParam("cf") String cf) {
		PersonaSearch ps = new PersonaSearch();
		ps.setCf(cf);
		ps.setCanc("N");
		List<Persona> ret = manager.cercaPersone(ps);
		List<PersonaDTO> wsret=new ArrayList<PersonaDTO>();
		for(Persona p:ret){
			PersonaDTO n=new PersonaDTO();
			n.setNome(p.getNome());
			n.setCognome(p.getCognome());
			n.setCodiceFiscale(p.getCf());
			
			//n.setEsenzioni(manager.loadEsenzioniDiPersona(p));
			//n.setMedici(manager.loadMediciDiPersona(p));
		}
		return Response.status(200).entity(wsret).build();
	}
	
	@GET
	@Path(value = "trovaMedico/{cf}")
	public Response trovaMedico(@PathParam("cf") String cf) {
		MedicoSearch ms = new MedicoSearch();
		ms.setCf(cf);
		ms.setCanc("N");
		List<Medico> ret = manager.cercaMedico(ms);
		List<MedicoDTO> wsret=new ArrayList<MedicoDTO>();
		for(Medico m:ret){
			MedicoDTO n=new MedicoDTO();
			n.setNome(m.getNome());
			n.setCognome(m.getCognome());
			n.setCodiceFiscale(m.getCf());
			wsret.add(n);
		}
		return Response.status(200).entity(wsret).build();
	}
	
	@POST
	@Path(value = "insertMedico")
	public Response insertMedico(Medico m) {		
		ResponseDTO r=new ResponseDTO();
		try {
			manager.registraMedico(m);
			r.setError("OK");
			r.setPayload(m);
			r.setCountTotal(1);
		}catch (Throwable e) {
			r.setError(e.getMessage());
			r.setPayload(m);
			r.setCountTotal(0);
		}			
		return Response.status(200).entity(r).build();
	}
	
	@DELETE
	@Path(value = "deleteMedico/{cf}")
	public Response deleteMedico(@PathParam("cf") String cf) {		
		MedicoSearch ms = new MedicoSearch();
		ms.setCf(cf);
		ms.setCanc("N");
		int count=manager.countMedico(ms);
		List<Medico> ret = manager.cercaMedico(ms);
		for(Medico m:ret){ 
			manager.deleteMedico(m);		
		}
		ResponseDTO r=new ResponseDTO();
		r.setError("Erroe");
		r.setPayload(ret);
		r.setCountTotal(count);
		return Response.status(200).entity(r).build();
	}
}
