package dao;

import java.util.List;

import model.Prestazione;
import model.PrestazioneSearch;

public interface PrestazioneDao {
	
	List<Prestazione> cerca(PrestazioneSearch ps);
	
	void insert(Prestazione p);
	
	void update(Prestazione p);
	
	void delete(Prestazione p);

}
