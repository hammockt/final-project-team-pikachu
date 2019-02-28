package rec.games.pokemon.teambuilder;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import java.io.Serializable;

public class PokeAPIUtils
{
	public static final String POKE_ITEM = "rec.games.pokemon.teambuilder.PokeAPIUtils";
	private final static String POKE_API_BASE_URL = "https://pokeapi.co/api/v2/";
	private final static String POKE_API_LIMIT_PARAM = "limit";
	private final static String POKE_API_OFFSET_PARAM = "offset";

	private final static String POKE_API_POKEMON_ENDPOINT = "pokemon";
	private final static String POKE_API_TYPE_ENDPOINT = "type";
	private final static String POKE_API_MOVE_ENDPOINT = "move";

	private final static String POKE_API_SPRITE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
	private final static String POKE_API_ARTWORK_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other-sprites/official-artwork/";
	private final static String POKE_API_SPRITE_FILE_TYPE = ".png";

	private final static String POKE_BULBAPEDIA_URL = "https://bulbapedia.bulbagarden.net/wiki/";
	private final static String POKE_BULBAPEDIA_END = "_(Pokémon)";

	static class NamedAPIResourceList implements Serializable
	{
		NamedAPIResource[] results;
		int count; 		//count is available
	}

	static class NamedAPIResource implements Serializable
	{
		String name;
		String url;
	}

	static class Name implements Serializable
	{
		String name;
		NamedAPIResource language;
	}

	static class Pokemon implements Serializable
	{
		int id;
		String name;
		PokemonMove[] moves;
		PokemonSprites sprites;
		PokemonType[] types;
	}

	static class PokemonMove implements Serializable
	{
		NamedAPIResource move;
		//version_group_details is available
	}

	static class PokemonSprites implements Serializable
	{
		String front_default;
		String back_default;
	}

	static class PokemonType implements Serializable
	{
		int slot;
		NamedAPIResource type;
	}

	static class Move implements Serializable
	{
		int id;
		String name;
		int power;
		Name[] names;
		NamedAPIResource type;
	}

	static class Type implements Serializable
	{
		int id;
		String name;
		TypeRelations damage_relations;
		Name[] names;
	}

	static class TypeRelations implements Serializable
	{
		NamedAPIResource[] no_damage_to;
		NamedAPIResource[] half_damage_to;
		NamedAPIResource[] double_damage_to;
		NamedAPIResource[] no_damage_from;
		NamedAPIResource[] half_damage_from;
		NamedAPIResource[] double_damage_from;
	}

	static String buildNamedAPIResourceListURL(String endPoint, int limit, int offset)
	{
		return Uri.parse(POKE_API_BASE_URL).buildUpon()
			.appendPath(endPoint)
			.appendQueryParameter(POKE_API_LIMIT_PARAM, String.valueOf(limit))
			.appendQueryParameter(POKE_API_OFFSET_PARAM, String.valueOf(offset))
			.build()
			.toString();
	}

	static String buildPokemonListURL(int limit, int offset)
	{
		return buildNamedAPIResourceListURL(POKE_API_POKEMON_ENDPOINT, limit, offset);
	}

	static String buildTypeListURL(int limit, int offset)
	{
		return buildNamedAPIResourceListURL(POKE_API_TYPE_ENDPOINT, limit, offset);
	}

	static String buildMoveListURL(int limit, int offset)
	{
		return buildNamedAPIResourceListURL(POKE_API_MOVE_ENDPOINT, limit, offset);
	}

	static NamedAPIResourceList parseNamedAPIResourceListJSON(String namedAPIResourceListJSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(namedAPIResourceListJSON, NamedAPIResourceList.class);
	}

	static int getId(String url)
	{
		if(url == null)
			return 0;

		String id = Uri.parse(url).getLastPathSegment();
		if(id != null)
			return Integer.parseInt(id);
		return 0;
	}

	static String getSpriteUrl(int id){
		return Uri.parse(POKE_API_SPRITE_URL).buildUpon()
			.appendEncodedPath(Integer.toString(id) + POKE_API_SPRITE_FILE_TYPE).build().toString();
	}

	static String getArtworkUrl(int id){
		return Uri.parse(POKE_API_ARTWORK_URL).buildUpon()
			.appendEncodedPath(Integer.toString(id) + POKE_API_SPRITE_FILE_TYPE).build().toString();
	}

	static Uri getBulbapediaPage(String poke){
		//takes in string of Pokemon name
		return Uri.parse(POKE_BULBAPEDIA_URL).buildUpon()
			.appendEncodedPath(poke + POKE_BULBAPEDIA_END).build();
	}

}
