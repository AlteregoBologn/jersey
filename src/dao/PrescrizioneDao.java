package dao;

import java.util.List;

import model.Prescrizione;
import model.PrescrizioneSearch;

public interface PrescrizioneDao {
	
	List<Prescrizione> cerca(PrescrizioneSearch ps);
	
	void insert(Prescrizione p);
	
	void update(Prescrizione p);
	
	void delete(Prescrizione p);

}
