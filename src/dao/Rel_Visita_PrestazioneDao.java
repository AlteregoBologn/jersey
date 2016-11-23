package dao;

import java.util.List;

import model.Rel_Visita_Prestazione;
import model.Rel_Visita_PrestazioneSearch;

public interface Rel_Visita_PrestazioneDao {
	  public List<Rel_Visita_Prestazione> loadAll(Rel_Visita_PrestazioneSearch s);
	  public Integer count(Rel_Visita_PrestazioneSearch s);
	  public void insert(Rel_Visita_Prestazione a);
	  public void update(Rel_Visita_Prestazione a);
	  public void delete(Rel_Visita_Prestazione a);
}
