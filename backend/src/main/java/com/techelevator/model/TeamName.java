package com.techelevator.model;

public class TeamName {
	private String teamName;
	private int wins;
	private int losses;
	private int tournamentWins;
	private int tournamentsEntered;
	
	public TeamName() {
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
	
	
}
