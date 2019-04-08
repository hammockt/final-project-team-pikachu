package rec.games.pokemon.teambuilder.model.db.veekun;

import android.arch.lifecycle.LiveData;

import java.util.List;

public class VeekunRepository
{
	private VeekunDao dao;

	public VeekunRepository(VeekunDao dao)
	{
		this.dao = dao;
	}

	public List<VeekunType> getTypes()
	{
		return dao.getTypes();
	}
}
