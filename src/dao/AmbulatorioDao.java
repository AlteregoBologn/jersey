package dao;

import java.util.List;

import model.Ambulatorio;
import model.AmbulatorioSearch;

public interface AmbulatorioDao {
	
	List<Ambulatorio> cerca(AmbulatorioSearch as);
	
	void insert(Ambulatorio a);
	
	void update(Ambulatorio a);

	void delete(Ambulatorio a);

}
