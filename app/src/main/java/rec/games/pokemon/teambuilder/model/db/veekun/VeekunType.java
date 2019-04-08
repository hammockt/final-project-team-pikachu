package rec.games.pokemon.teambuilder.model.db.veekun;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

import java.util.HashMap;

public class VeekunType
{
	public int id;
	public String identifier;

	@ColumnInfo(name = "locale_ids")
	String localeIds;
	String names;

	@ColumnInfo(name = "damage_ids")
	String damageIds;
	@ColumnInfo(name = "damage_mults")
	String damageMultipliers;
}
