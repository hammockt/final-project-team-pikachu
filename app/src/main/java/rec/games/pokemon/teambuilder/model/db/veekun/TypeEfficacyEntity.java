package rec.games.pokemon.teambuilder.model.db.veekun;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

@Entity(tableName = "type_efficacy",
	primaryKeys = {"damage_type_id", "target_type_id"},
	foreignKeys =
		{
			@ForeignKey(entity = TypeEntity.class, parentColumns = "id", childColumns = "damage_type_id"),
			@ForeignKey(entity = TypeEntity.class, parentColumns = "id", childColumns = "target_type_id")
		},
	indices =
		{
			@Index(name = "fk_type-efficacy_damage-type-id", value = "damage_type_id"),
			@Index(name = "fk_type-efficacy_target-type-id", value = "target_type_id")
		}
)
public class TypeEfficacyEntity
{
	@ColumnInfo(name = "damage_type_id")
	int damageTypeId;

	@ColumnInfo(name = "target_type_id")
	int targetTypeId;

	@ColumnInfo(name = "damage_factor")
	@NonNull
	int damageFactor;
}
