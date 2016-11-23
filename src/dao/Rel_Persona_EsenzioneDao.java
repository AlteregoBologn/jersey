package dao;

import java.util.List;

import model.Rel_Persona_Esenzione;
import model.Rel_Persona_EsenzioneSearch;

public interface Rel_Persona_EsenzioneDao {
	
	  public List<Rel_Persona_Esenzione> loadAll(Rel_Persona_EsenzioneSearch s);
	  public Integer count(Rel_Persona_EsenzioneSearch s);
	  public void insert(Rel_Persona_Esenzione a);
	  public void update(Rel_Persona_Esenzione a);
	  public void delete(Rel_Persona_Esenzione a);
	  
	}
