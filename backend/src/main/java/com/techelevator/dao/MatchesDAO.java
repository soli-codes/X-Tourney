package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Matches;
import com.techelevator.model.TeamName;

public interface MatchesDAO {

	List<Matches> getAllMatches();
	
	Matches getMatchById(int matchId);
	
	void createMatch(int tournamentSize, int tournamentId, TeamName[] teams);
	
	void updateMatch(Matches match, int nextMatchId);
	
	void deleteMatch(int matchId);
}
