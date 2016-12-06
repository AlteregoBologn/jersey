package dao;

import java.util.List;

import model.Codifica;
import model.CodificaSearch;

public interface CodificaDao {
	
public List<Codifica> loadAll(CodificaSearch c);
	
	public Integer count(CodificaSearch c);
	
	public void insert(Codifica c);
	
	public void update(Codifica c);
	
	public void delete(Codifica c);

}
