package dao;

import java.util.List;

import model.Esenzione;
import model.EsenzioneSearch;
import model.MedicoSearch;

public interface EsenzioneDao {
	
	List<Esenzione> cerca(EsenzioneSearch es);
	
	int count(EsenzioneSearch es);
	
	void insert(Esenzione e);
	
	void update(Esenzione e);
	
	void delete(Esenzione e);

}
