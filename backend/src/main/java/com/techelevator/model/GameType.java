package com.techelevator.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class GameType {
	
	@Positive(message = "Must be a positive numeric value")
	private int gameTypeId;
	
	@NotNull(message = "must contain a game type name")
	private String gameType;
	
	public GameType() {
	}

	public int getGameTypeId() {
		return gameTypeId;
	}

	public void setGameTypeId(int gameTypeId) {
		this.gameTypeId = gameTypeId;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
	
}
