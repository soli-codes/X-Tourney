package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Tournament;

public interface TournamnetDAO {
	
	List<Tournament> listAllTournaments();
	
	Tournament getTournamentById(int tournamentId);
	
	Tournament createTournament(Tournament tournament);
	
	Void deleteTournament(int tournamentId);

}
