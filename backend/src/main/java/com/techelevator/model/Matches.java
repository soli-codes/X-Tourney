package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;


public class Matches {
	private int tournamentId;
	private int teamOneId;
	private int teamTwoId;
	private int winningTeamId;
	private int losingTeamId;
	private int winningTeamScore;
	private int losingTeamScore;
	private LocalDate matchDate;
	private LocalTime matchTime;
	
	public Matches() {
	}

	public int getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}

	public int getTeamOneId() {
		return teamOneId;
	}

	public void setTeamOneId(int teamOneId) {
		this.teamOneId = teamOneId;
	}

	public int getTeamTwoId() {
		return teamTwoId;
	}

	public void setTeamTwoId(int teamTwoId) {
		this.teamTwoId = teamTwoId;
	}

	public int getWinningTeamId() {
		return winningTeamId;
	}

	public void setWinningTeamId(int winningTeamId) {
		this.winningTeamId = winningTeamId;
	}

	public int getLosingTeamId() {
		return losingTeamId;
	}

	public void setLosingTeamId(int losingTeamId) {
		this.losingTeamId = losingTeamId;
	}

	public int getWinningTeamScore() {
		return winningTeamScore;
	}

	public void setWinningTeamScore(int winningTeamScore) {
		this.winningTeamScore = winningTeamScore;
	}

	public int getLosingTeamScore() {
		return losingTeamScore;
	}

	public void setLosingTeamScore(int losingTeamScore) {
		this.losingTeamScore = losingTeamScore;
	}

	public LocalDate getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(LocalDate matchDate) {
		this.matchDate = matchDate;
	}

	public LocalTime getMatchTime() {
		return matchTime;
	}

	public void setTime(LocalTime matchTime) {
		this.matchTime = matchTime;
	}
	
	
}


