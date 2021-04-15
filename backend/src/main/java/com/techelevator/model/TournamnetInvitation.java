package com.techelevator.model;

import javax.validation.constraints.Positive;

public class TournamnetInvitation {
	
	@Positive(message = "Must be a positive numeric value")
	private int tournamentId;
	
	@Positive(message = "Must be a positive numeric value")
	private int teamId;
	private String inviteStatus;
	
	public int getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
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
