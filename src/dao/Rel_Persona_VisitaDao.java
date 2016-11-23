package dao;

import java.util.List;

import model.Rel_Persona_Visita;
import model.Rel_Persona_VisitaSearch;

public interface Rel_Persona_VisitaDao {
  public List<Rel_Persona_Visita> loadAll(Rel_Persona_VisitaSearch s);
  public Integer count(Rel_Persona_VisitaSearch s);
  public void insert(Rel_Persona_Visita a);
  public void update(Rel_Persona_Visita a);
  public void delete(Rel_Persona_Visita a);
}