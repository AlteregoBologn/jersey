package dao;

import java.util.List;

import model.Rel_Prescrizione_Prestazione;
import model.Rel_Prescrizione_PrestazioneSearch;

public interface Rel_Prescrizione_PrestazioneDao {
	  public List<Rel_Prescrizione_Prestazione> loadAll(Rel_Prescrizione_PrestazioneSearch s);
	  public Integer count(Rel_Prescrizione_PrestazioneSearch s);
	  public void insert(Rel_Prescrizione_Prestazione a);
	  public void update(Rel_Prescrizione_Prestazione a);
	  public void delete(Rel_Prescrizione_Prestazione a);
}
