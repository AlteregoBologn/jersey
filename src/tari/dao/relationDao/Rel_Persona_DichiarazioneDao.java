package tari.dao.relationDao;

import java.util.List;

import tari.model.relationModel.Rel_PersonaTari_Dichiarazione;
import tari.model.relationModel.Rel_PersonaTari_DichiarazioneSearch;

public interface Rel_Persona_DichiarazioneDao {
	public List<Rel_PersonaTari_Dichiarazione> loadAll(Rel_PersonaTari_DichiarazioneSearch s);
	public Integer count(Rel_PersonaTari_DichiarazioneSearch s);
	public void insert(Rel_PersonaTari_Dichiarazione a);
	public void update(Rel_PersonaTari_Dichiarazione a);
	public void delete(Rel_PersonaTari_Dichiarazione a);
}