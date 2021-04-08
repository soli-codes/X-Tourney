package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Matches;

public interface MatchesDAO {

	List<Matches> getAllMatches();
	
	Matches getMatchById(int matchId);
	
	Matches createMatch(Matches match);
	
	void updateMatch(Matches match);
	
	void deleteMatch(int matchId);
}
