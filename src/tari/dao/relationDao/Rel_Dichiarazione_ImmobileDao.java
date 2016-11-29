package tari.dao.relationDao;

import java.util.List;

import tari.model.relationModel.Rel_Dichiarazione_Immobile;
import tari.model.relationModel.Rel_Dichiarazione_ImmobileSearch;

public interface Rel_Dichiarazione_ImmobileDao {
	public List<Rel_Dichiarazione_Immobile> loadAll(Rel_Dichiarazione_ImmobileSearch s);
	public Integer count(Rel_Dichiarazione_ImmobileSearch s);
	public void insert(Rel_Dichiarazione_Immobile a);
	public void update(Rel_Dichiarazione_Immobile a);
	public void delete(Rel_Dichiarazione_Immobile a);
}
