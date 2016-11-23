package dao;

import java.util.List;

import model.Rel_Medico_Visita;
import model.Rel_Medico_VisitaSearch;

public interface Rel_Medico_VisitaDao {
	  public List<Rel_Medico_Visita> loadAll(Rel_Medico_VisitaSearch s);
	  public Integer count(Rel_Medico_VisitaSearch s);
	  public void insert(Rel_Medico_Visita a);
	  public void update(Rel_Medico_Visita a);
	  public void delete(Rel_Medico_Visita a);
	}
