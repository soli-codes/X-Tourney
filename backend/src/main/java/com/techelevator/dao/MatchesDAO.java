package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Matches;
import com.techelevator.model.TeamName;

public interface MatchesDAO {

	List<Matches> getAllMatches();
	
	Matches getMatchById(int matchId);
	
	void createMatch(Integer tournamentSize, Integer tournamentId, TeamName[] teams);
	
	void updateMatch(Matches match, int nextMatchId);
	
	void deleteMatch(int matchId);
}
