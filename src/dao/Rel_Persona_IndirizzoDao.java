package dao;

import java.util.List;

import model.Rel_Persona_Indirizzo;
import model.Rel_Persona_IndirizzoSearch;

public interface Rel_Persona_IndirizzoDao {
	public List<Rel_Persona_Indirizzo> loadAll(Rel_Persona_IndirizzoSearch s);
	public Integer count(Rel_Persona_IndirizzoSearch s);
	public void insert(Rel_Persona_Indirizzo a);
	public void update(Rel_Persona_Indirizzo a);
	public void delete(Rel_Persona_Indirizzo a);
}
