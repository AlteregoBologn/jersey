package tari.dao;

import java.util.List;

import tari.model.PersonaTari;
import tari.model.PersonaTariSearch;

public interface PersonaTariDao {

	List<PersonaTari> loadAll(PersonaTariSearch ps);

	Integer count (PersonaTariSearch ps);

	void insert(PersonaTari p);

	void update(PersonaTari p);

	void delete(PersonaTari p);
	
	void cambiaPassword(PersonaTari p);

}
