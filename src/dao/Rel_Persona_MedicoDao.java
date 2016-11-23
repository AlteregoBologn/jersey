package dao;

import java.util.List;

import model.Rel_Persona_Medico;
import model.Rel_Persona_MedicoSearch;

public interface Rel_Persona_MedicoDao {
	  public List<Rel_Persona_Medico> loadAll(Rel_Persona_MedicoSearch s);
	  public Integer count(Rel_Persona_MedicoSearch s);
	  public void insert(Rel_Persona_Medico a);
	  public void update(Rel_Persona_Medico a);
	  public void delete(Rel_Persona_Medico a);
	}
