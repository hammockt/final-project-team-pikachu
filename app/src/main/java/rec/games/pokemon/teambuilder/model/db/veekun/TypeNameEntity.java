package rec.games.pokemon.teambuilder.model.db.veekun;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

@Entity(tableName = "type_names",
	primaryKeys = {"type_id", "local_language_id"},
	foreignKeys = @ForeignKey(entity = TypeEntity.class, parentColumns = "id", childColumns = "type_id"),
	indices = @Index(name = "fk_type-names_type-id", value = "type_id")
)
public class TypeNameEntity
{
	@ColumnInfo(name = "type_id")
	int typeId;

	@ColumnInfo(name = "local_language_id")
	int localLanguageId;

	String name;
}
