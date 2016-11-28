package tari.dao;

import java.util.List;

import tari.model.Dichiarazione;
import tari.model.DichiarazioneSearch;

public interface DichiarazioneDao {
	public List<Dichiarazione> loadAll(DichiarazioneSearch s);
	public Integer count(DichiarazioneSearch s);
	public void insert(Dichiarazione a);
	public void update(Dichiarazione a);
	public void delete(Dichiarazione a);
}
