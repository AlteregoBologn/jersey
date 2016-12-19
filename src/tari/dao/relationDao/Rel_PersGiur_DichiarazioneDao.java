package tari.dao.relationDao;

import java.util.List;

import tari.model.relationModel.Rel_PersGiur_Dichiarazione;
import tari.model.relationModel.Rel_PersGiur_DichiarazioneSearch;

public interface Rel_PersGiur_DichiarazioneDao {
	
	public List<Rel_PersGiur_Dichiarazione> loadAll(Rel_PersGiur_DichiarazioneSearch s);

	public Integer count(Rel_PersGiur_DichiarazioneSearch s);

	public void insert(Rel_PersGiur_Dichiarazione a);

	public void update(Rel_PersGiur_Dichiarazione a);

	public void delete(Rel_PersGiur_Dichiarazione a);
}
