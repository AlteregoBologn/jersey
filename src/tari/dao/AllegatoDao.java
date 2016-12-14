package tari.dao;

import java.util.List;

import tari.model.Allegato;
import tari.model.AllegatoSearch;

public interface AllegatoDao {
	
	public List<Allegato> loadAll (AllegatoSearch allegatoSearch);

	public void insert(Allegato allegato);

	public void update(Allegato allegato);

	public void delete(Allegato allegato);

}
