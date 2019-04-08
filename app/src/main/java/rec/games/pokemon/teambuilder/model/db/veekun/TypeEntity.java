package rec.games.pokemon.teambuilder.model.db.veekun;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "types")
public class TypeEntity
{
	@PrimaryKey
	public int id;

	@NonNull
	public String identifier;

	@ColumnInfo(name = "generation_id")
	@NonNull
	public int generationId;

	@ColumnInfo(name = "damage_class_id")
	public int damageClassId;
}
