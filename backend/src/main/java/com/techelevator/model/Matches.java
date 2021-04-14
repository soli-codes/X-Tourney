package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class Matches {
	
	@NotNull
	private int matchId;
	
	@NotNull
	private int tournamentId;
	
	@NotNull
	private int teamOneId;
	
	@NotNull
	private int teamTwoId;
	private int winningTeamId;
	private int losingTeamId;
	private int winningTeamScore;
	private int losingTeamScore;
	private String matchDate;
	private String matchTime;
	private int nextMatch;
	
	
	public Matches() {
	}
	
	public int getMatchId() {
		return matchId;
	}
	
	public void setMatchId(int matchId) {
		this.matchId = matchId;
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

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public String getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}
	
	public int getNextMatch() {
		return nextMatch;
	}
	
	public void setNextMatch(int nextMatch) {
		this.nextMatch = nextMatch;
	}
	
	
}


