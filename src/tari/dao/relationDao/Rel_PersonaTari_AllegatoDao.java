package tari.dao.relationDao;

import java.util.List;

import tari.model.relationModel.Rel_PersonaTari_Allegato;
import tari.model.relationModel.Rel_PersonaTari_AllegatoSearch;

public interface Rel_PersonaTari_AllegatoDao {

	public List<Rel_PersonaTari_Allegato> loadAll(Rel_PersonaTari_AllegatoSearch rel_PersonaTari_AllegatoSearch);

	public Integer count(Rel_PersonaTari_AllegatoSearch s);

	public void insert(Rel_PersonaTari_Allegato rel_PersonaTari_Allegato);

	public void update(Rel_PersonaTari_Allegato rel_PersonaTari_Allegato);

	public void delete(Rel_PersonaTari_Allegato rel_PersonaTari_Allegato);
}
