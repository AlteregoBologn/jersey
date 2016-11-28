package tari.dao;

import java.util.List;

import tari.model.Locale;
import tari.model.LocaleSearch;

public interface LocaleDao {
	  public List<Locale> loadAll(LocaleSearch s);
	  public Integer count(LocaleSearch s);
	  public void insert(Locale a);
	  public void update(Locale a);
	  public void delete(Locale a);
	}