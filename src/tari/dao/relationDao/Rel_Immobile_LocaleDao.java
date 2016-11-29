package tari.dao.relationDao;

import java.util.List;

import tari.model.relationModel.Rel_Immobile_Locale;
import tari.model.relationModel.Rel_Immobile_LocaleSearch;

public interface Rel_Immobile_LocaleDao {
	  public List<Rel_Immobile_Locale> loadAll(Rel_Immobile_LocaleSearch s);
	  public Integer count(Rel_Immobile_LocaleSearch s);
	  public void insert(Rel_Immobile_Locale a);
	  public void update(Rel_Immobile_Locale a);
	  public void delete(Rel_Immobile_Locale a);
	}
