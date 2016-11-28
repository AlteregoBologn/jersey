package tari.dao;

import java.util.List;

import tari.model.Immobile;
import tari.model.ImmobileSearch;

public interface ImmobileDao {
	public List<Immobile> loadAll(ImmobileSearch s);
	public Integer count(ImmobileSearch s);
	public void insert(Immobile a);
	public void update(Immobile a);
	public void delete(Immobile a);
}		