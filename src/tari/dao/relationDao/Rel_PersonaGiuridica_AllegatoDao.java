package tari.dao.relationDao;

import java.util.List;

import tari.model.relationModel.Rel_PersonaGiuridica_Allegato;
import tari.model.relationModel.Rel_PersonaGiuridica_AllegatoSearch;


public interface Rel_PersonaGiuridica_AllegatoDao {

	public List<Rel_PersonaGiuridica_Allegato> loadAll(Rel_PersonaGiuridica_AllegatoSearch rel_PersonaGiuridica_AllegatoSearch);

	public Integer count(Rel_PersonaGiuridica_AllegatoSearch rel_PersonaGiuridica_AllegatoSearch);

	public void insert(Rel_PersonaGiuridica_Allegato rel_PersonaGiuridica_Allegato);

	public void update(Rel_PersonaGiuridica_Allegato rel_PersonaGiuridica_Allegato);

	public void delete(Rel_PersonaGiuridica_Allegato rel_PersonaGiuridica_Allegato);
}
