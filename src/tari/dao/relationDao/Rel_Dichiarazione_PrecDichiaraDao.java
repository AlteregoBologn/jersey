package tari.dao.relationDao;

import java.util.List;

import tari.model.relationModel.Rel_Dichiarazione_PrecDichiara;
import tari.model.relationModel.Rel_Dichiarazione_PrecDichiaraSearch;

public interface Rel_Dichiarazione_PrecDichiaraDao {
	  public List<Rel_Dichiarazione_PrecDichiara> loadAll(Rel_Dichiarazione_PrecDichiaraSearch s);
	  public Integer count(Rel_Dichiarazione_PrecDichiaraSearch s);
	  public void insert(Rel_Dichiarazione_PrecDichiara a);
	  public void update(Rel_Dichiarazione_PrecDichiara a);
	  public void delete(Rel_Dichiarazione_PrecDichiara a);
	}
