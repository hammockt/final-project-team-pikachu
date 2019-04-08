package rec.games.pokemon.teambuilder.model.db.savedteam;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "teams")
public class SavedTeamEntity
{
	@PrimaryKey(autoGenerate = true)
	public int id;

	public String name;
}