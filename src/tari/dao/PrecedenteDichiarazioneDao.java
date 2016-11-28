package tari.dao;

import java.util.List;

import tari.model.PrecedenteDichiarazione;
import tari.model.PrecedenteDichiarazioneSearch;

public interface PrecedenteDichiarazioneDao {
	  public List<PrecedenteDichiarazione> loadAll(PrecedenteDichiarazioneSearch s);
	  public Integer count(PrecedenteDichiarazioneSearch s);
	  public void insert(PrecedenteDichiarazione a);
	  public void update(PrecedenteDichiarazione a);
	  public void delete(PrecedenteDichiarazione a);
	}