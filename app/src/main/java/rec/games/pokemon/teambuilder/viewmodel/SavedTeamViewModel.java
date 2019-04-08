package rec.games.pokemon.teambuilder.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import rec.games.pokemon.teambuilder.model.Pokemon;
import rec.games.pokemon.teambuilder.model.Team;
import rec.games.pokemon.teambuilder.model.TeamMember;
import rec.games.pokemon.teambuilder.model.db.savedteam.SavedTeam;
import rec.games.pokemon.teambuilder.model.db.savedteam.SavedTeamRepository;
import rec.games.pokemon.teambuilder.model.repository.PokeAPIRepository;

public class SavedTeamViewModel extends ViewModel
{
	private SavedTeamRepository repo = new SavedTeamRepository();

	public LiveData<Team> getTeam(int teamId)
	{
		LiveData<SavedTeam> liveSavedTeam = repo.getTeamById(teamId);
		return Transformations.map(liveSavedTeam, new Function<SavedTeam, Team>()
			{
				@Override
				public Team apply(SavedTeam savedTeam)
				{
					Team team = new Team();
					if(savedTeam != null && savedTeam.memberIds != null)
					{
						for(int pokemonId : savedTeam.memberIds)
						{
							TeamMember m = new TeamMember();
							m.pokemon = PokeAPIRepository.getLivePokemon(pokemonId);
							team.members.add(m);
						}
					}
					return team;
				}
			}
		);
	}

	public LiveData<Boolean> isPokemonInTeam(int teamId, int pokemonId)
	{
		return repo.isPokemonInTeam(teamId, pokemonId);
	}

	public LiveData<List<SavedTeam>> getAllTeams()
	{
		return repo.getAllTeams();
	}

	public void createSavedTeam(SavedTeam team)
	{
		repo.createSavedTeam(team);
	}

	public void deleteSavedTeam(SavedTeam team)
	{
		repo.deleteSavedTeam(team);
	}

	public void addPokemonToCurrentTeam(int teamId, Pokemon pokemon)
	{
		SavedTeam savedTeam = new SavedTeam();
		savedTeam.id = teamId;
		repo.createSavedTeam(savedTeam); // in case it hasn't been created yet
		repo.addTeamMember(savedTeam, pokemon.getId());
	}

	public void removePokemonFromCurrentTeam(int teamId, Pokemon pokemon)
	{
		SavedTeam savedTeam = new SavedTeam();
		savedTeam.id = teamId;
		repo.removeTeamMember(savedTeam, pokemon.getId());
	}
}
