package dao;

import java.util.List;

import model.Decodifica;
import model.DecodificaSearch;

public interface DecodificaDao {
	public List<Decodifica> loadAll(DecodificaSearch s);
	public Integer count(DecodificaSearch s);
	public void insert(Decodifica a);
	public void update(Decodifica a);
	public void delete(Decodifica a);
}
