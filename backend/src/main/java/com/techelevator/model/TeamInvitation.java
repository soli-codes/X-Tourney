package com.techelevator.model;

import javax.validation.constraints.Positive;

public class TeamInvitation {
	
	@Positive(message = "Must be a positive numeric value")
	private int userId;
	
	@Positive(message = "Must be a positive numeric value")
	private int teamId;
	private String inviteStatus;
	
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
	public String getInviteStatus() {
		return inviteStatus;
	}
	public void setInviteStatus(String inviteStatus) {
		this.inviteStatus = inviteStatus;
	}
	
}
