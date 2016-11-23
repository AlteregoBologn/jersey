package dao;

import java.util.List;

import model.Rel_Medico_Ambulatorio;
import model.Rel_Medico_AmbulatorioSearch;

public interface Rel_Medico_AmbulatorioDao {
	  public List<Rel_Medico_Ambulatorio> loadAll(Rel_Medico_AmbulatorioSearch s);
	  public Integer count(Rel_Medico_AmbulatorioSearch s);
	  public void insert(Rel_Medico_Ambulatorio a);
	  public void update(Rel_Medico_Ambulatorio a);
	  public void delete(Rel_Medico_Ambulatorio a);
	}