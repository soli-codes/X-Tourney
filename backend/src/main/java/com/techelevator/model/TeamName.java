package com.techelevator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TeamName {
	
	//@Positive(message = "must be a positve numeric value")
	private  int teamId;
	
	@NotNull(message = "must contain a name")
	private String teamName;
	
	@Min(value = 0, message = "Must be a non-negative numeric value")
	private int wins;
	
	@Min(value = 0, message = "Must be a non-negative numeric value")
	private int losses;
	
	@Min(value = 0, message = "Must be a non-negative numeric value")
	private int tournamentWins;
	
	@Min(value = 0, message = "Must be a non-negative numeric value")
	private int tournamentsEntered;
	
	private String teamImage;
	
	public TeamName() {
	}
	
	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}


	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getTournamentWins() {
		return tournamentWins;
	}

	public void setTournamentWins(int tournamentWins) {
		this.tournamentWins = tournamentWins;
	}

	public int getTournamentsEntered() {
		return tournamentsEntered;
	}

	public void setTournamentsEntered(int tournamentsEntered) {
		this.tournamentsEntered = tournamentsEntered;
	}
	
	public String getTeamImage() {
		return teamImage;
	}
	
	public void setTeamImage(String teamImage) {
		this.teamImage = teamImage;
	}
	
}
