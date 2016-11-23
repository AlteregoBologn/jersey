package dao;

import java.util.List;

import model.Esenzione;
import model.EsenzioneSearch;

public interface EsenzioneDao {
	
	List<Esenzione> cerca(EsenzioneSearch es);
	
	void insert(Esenzione e);
	
	void update(Esenzione e);
	
	void delete(Esenzione e);

}
