package rec.games.pokemon.teambuilder.model.db.veekun;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.huma.room_for_asset.RoomAsset;

@Database(entities = {TypeEntity.class, TypeNameEntity.class, TypeEfficacyEntity.class},
	version = 2,
	exportSchema = false
)
public abstract class VeekunDatabase extends RoomDatabase
{
	public static VeekunDatabase createDatabase(Context context)
	{
		return RoomAsset.databaseBuilder(context.getApplicationContext(),
			VeekunDatabase.class,
			"veekun.db")
			.allowMainThreadQueries()
			.build();
	}

	public abstract VeekunDao veekunDao();
}
