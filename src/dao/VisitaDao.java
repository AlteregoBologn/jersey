package dao;

import java.util.List;

import model.Visita;
import model.VisitaSearch;

public interface VisitaDao {
	public List<Visita> loadAll(VisitaSearch s);
	public Integer count(VisitaSearch s);
	public void insert(Visita a);
	public void update(Visita a);
	public void delete(Visita a);
}
