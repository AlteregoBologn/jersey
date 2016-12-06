package dao;

import java.util.List;

import model.Indirizzo;
import model.IndirizzoSearch;

public interface IndirizzoDao {
	public List<Indirizzo> loadAll(IndirizzoSearch s);
	public Integer count(IndirizzoSearch s);
	public void insert(Indirizzo a);
	public void update(Indirizzo a);
	public void delete(Indirizzo a);	
}
