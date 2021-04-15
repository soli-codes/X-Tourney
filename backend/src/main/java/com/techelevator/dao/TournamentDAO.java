package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Matches;
import com.techelevator.model.TeamName;
import com.techelevator.model.Tournament;

public interface TournamentDAO {
	
	List<Tournament> listAllTournaments();
	
	Tournament getTournamentById(int tournamentId);
	
	Tournament createTournament(Tournament tournament);
	
	void deleteTournament(int tournamentId);
	
	List<TeamName> getTeamsByTournamentId(int tournamentId);
	
	public List<List<Matches>> getMatchesBytournamentId(int tournamentId);
	
	public List<Tournament>getTournamentsByHostId(int hostId);
	
	public List<Tournament>getTournamentsByUserId(int userId);
	
	public void addTeamToTournament(TeamName team, int tournamentId);
	
	public List<TeamName> getAllTeamsThatAreNotInTournament(int tournamentId);

}
