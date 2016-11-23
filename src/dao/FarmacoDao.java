package dao;

import java.util.List;

import model.Farmaco;
import model.FarmacoSearch;

public interface FarmacoDao {
	
	List<Farmaco> cerca(FarmacoSearch fs);
	
	void insert(Farmaco f);
	
	void update(Farmaco f);
	
	void delete(Farmaco f);

}
