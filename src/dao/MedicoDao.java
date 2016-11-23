package dao;

import java.util.List;

import model.Medico;
import model.MedicoSearch;

public interface MedicoDao {
	
	
	
	List<Medico> cerca(MedicoSearch ms);
	
	int count(MedicoSearch ms);
	
	void insert(Medico m);
	
	void update(Medico m);
	
	void delete(Medico m);

}
