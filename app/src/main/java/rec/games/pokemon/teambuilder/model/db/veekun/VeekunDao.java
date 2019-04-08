package rec.games.pokemon.teambuilder.model.db.veekun;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public abstract class VeekunDao
{
	@Query("select\n" +
		"\tt.id, t.identifier,\n" +
		"\ttn.locale_ids, tn.names,\n" +
		"\tte.damage_ids, te.damage_mults\n" +
		"from types as t\n" +
		"inner join\n" +
		"(\n" +
		"\tselect\n" +
		"\t\ttype_id,\n" +
		"\t\tgroup_concat(local_language_id) as locale_ids,\n" +
		"\t\tgroup_concat(name) as names\n" +
		"\tfrom type_names\n" +
		"\tgroup by type_id\n" +
		") as tn on t.id = tn.type_id\n" +
		"inner join\n" +
		"(\n" +
		"\tselect\n" +
		"\t\tdamage_type_id,\n" +
		"\t\tgroup_concat(target_type_id) as damage_ids,\n" +
		"\t\tgroup_concat(damage_factor) as damage_mults\n" +
		"\tfrom type_efficacy\n" +
		"\twhere damage_factor != 100\n" +
		"\tgroup by damage_type_id\n" +
		") as te on t.id = te.damage_type_id\n" +
		"where t.id < 10000")
	public abstract List<VeekunType> getTypes();
}
