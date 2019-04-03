package rec.games.pokemon.teambuilder.model.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

@Database(entities = {SavedTeamEntity.class, SavedTeamMemberEntity.class}, version = 2)
public abstract class SavedTeamDatabase extends RoomDatabase
{
	static final Migration MIGRATION_1_2 = new Migration(1, 2)
	{
		@Override
		public void migrate(SupportSQLiteDatabase database)
		{
			database.execSQL("ALTER TABLE teams ADD COLUMN name TEXT");
			database.execSQL("CREATE TABLE team_member_moves (member_id INTEGER, move_id INTEGER, PRIMARY KEY(member_id, move_id))");
		}
	};
	private static SavedTeamDatabase INSTANCE;

	public static SavedTeamDatabase getDatabase(final Context context)
	{
		synchronized(SavedTeamDatabase.class)
		{
			if(INSTANCE == null)
			{
				INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SavedTeamDatabase.class, "teams.db")
					.addMigrations(MIGRATION_1_2)
					.build();
			}
			return INSTANCE;
		}
	}

	public abstract SavedTeamDao savedTeamDao();
}
