package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Tournament;

public interface TournamnetDAO {
	
	List<Tournament> listAllTournaments();
	
	Tournament getTournamentById(int tournamentId);
	
	List<Tournament> getTournamentByGameTypeId(int gameTypeId);
	
	//Get tournaments if still open for registration
	List<Tournament> getTournamentIfIsOpen();
	
	List<Tournament> getPastTournaments();
	
	//Get tournaments that aren't open for registration but are still active
	List<Tournament> getActiveTournaments();
	
	List<Tournament> getTournamentsbByEliminationType(String type);
	
	Tournament createTournament(Tournament tournament);
	
	Void deleteTournament(int tournamentId);

}
