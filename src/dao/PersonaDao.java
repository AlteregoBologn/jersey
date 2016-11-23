package dao;

import java.util.List;

import model.Persona;
import model.PersonaSearch;

public interface PersonaDao {

	List<Persona> cerca(PersonaSearch ps);

	Integer count (PersonaSearch ps);

	void insert(Persona p);

	void update(Persona p);

	void delete(Persona p);
	
	void cambiaPassword(Persona p);

}
