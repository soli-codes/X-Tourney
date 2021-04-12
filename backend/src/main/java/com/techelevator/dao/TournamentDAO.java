package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.TeamName;
import com.techelevator.model.Tournament;

public interface TournamentDAO {
	
	List<Tournament> listAllTournaments();
	
	Tournament getTournamentById(int tournamentId);
	
	Tournament createTournament(Tournament tournament);
	
	void deleteTournament(int tournamentId);
	
	List<TeamName> getTeamsByTournamentId(int tournamentId);

}
