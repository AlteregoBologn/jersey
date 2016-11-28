package tari.dao;

import java.util.List;

import tari.model.PersonaGiuridica;
import tari.model.PersonaGiuridicaSearch;

public interface PersonaGiuridicaDao {
	public List<PersonaGiuridica> loadAll(PersonaGiuridicaSearch s);
	public Integer count(PersonaGiuridicaSearch s);
	public void insert(PersonaGiuridica a);
	public void update(PersonaGiuridica a);
	public void delete(PersonaGiuridica a);
}