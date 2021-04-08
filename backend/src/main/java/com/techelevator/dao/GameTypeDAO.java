package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.GameType;

public interface GameTypeDAO {
	
	List<GameType> listAllGameTypes();
	
	GameType getGameTypeById(int gameTypeId);
	
	GameType createGameType(GameType gameType);
	
	void deleteGameType(int gameTypeId);
	
}
