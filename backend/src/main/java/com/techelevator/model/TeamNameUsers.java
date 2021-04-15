package com.techelevator.model;

import javax.validation.constraints.Min;

public class TeamNameUsers {

	@Min(value = 1, message = "Must be a non-negative numeric value")
	private int userId;
	
	@Min(value = 1, message = "Must be a non-negative numeric value")
	private int teamId;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getTeamId() {
		return teamId;
	}
	
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
}
