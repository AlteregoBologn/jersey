package dao;

import java.util.List;

import model.Citta;
import model.CittaSearch;

public interface CittaDao {
	
	public List<Citta> loadAll(CittaSearch s);
	
	public Integer count(CittaSearch s);
	
	public void insert(Citta a);
	
	public void update(Citta a);
	
	public void delete(Citta a);
}
