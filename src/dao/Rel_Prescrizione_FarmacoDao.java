package dao;

import java.util.List;

import model.Rel_Prescrizione_Farmaco;
import model.Rel_Prescrizione_FarmacoSearch;

public interface Rel_Prescrizione_FarmacoDao {
	  public List<Rel_Prescrizione_Farmaco> loadAll(Rel_Prescrizione_FarmacoSearch s);
	  public Integer count(Rel_Prescrizione_FarmacoSearch s);
	  public void insert(Rel_Prescrizione_Farmaco a);
	  public void update(Rel_Prescrizione_Farmaco a);
	  public void delete(Rel_Prescrizione_Farmaco a);
	}