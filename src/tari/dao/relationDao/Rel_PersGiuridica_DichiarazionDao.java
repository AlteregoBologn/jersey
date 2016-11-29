package tari.dao.relationDao;

import java.util.List;

import tari.model.relationModel.Rel_PersGiuridica_Dichiarazion;
import tari.model.relationModel.Rel_PersGiuridica_DichiarazionSearch;

public interface Rel_PersGiuridica_DichiarazionDao {
	public List<Rel_PersGiuridica_Dichiarazion> loadAll(Rel_PersGiuridica_DichiarazionSearch s);
	public Integer count(Rel_PersGiuridica_DichiarazionSearch s);
	public void insert(Rel_PersGiuridica_Dichiarazion a);
	public void update(Rel_PersGiuridica_Dichiarazion a);
	public void delete(Rel_PersGiuridica_Dichiarazion a);
}
