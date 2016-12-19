package tari.dao.relationDao;

import java.util.List;

import tari.model.relationModel.Rel_Dichiarazione_ImmobileSearch;
import tari.model.relationModel.Rel_PersonaTari_PersonaGiuridica;
import tari.model.relationModel.Rel_PersonaTari_PersonaGiuridicaSearch;

public interface Rel_PersonaTari_PersonaGiuridicaDao {
	
	public List<Rel_PersonaTari_PersonaGiuridica> loadAll(Rel_PersonaTari_PersonaGiuridicaSearch rel_personaTari_personaGiuridicaSearch);

	public Integer count(Rel_PersonaTari_PersonaGiuridica rel_personaTari_personaGiuridica);

	public void insert(Rel_PersonaTari_PersonaGiuridica rel_personaTari_personaGiuridica);

	public void update(Rel_PersonaTari_PersonaGiuridica arel_personaTari_personaGiuridica);

	public void delete(Rel_PersonaTari_PersonaGiuridica rel_personaTari_personaGiuridica);
}
