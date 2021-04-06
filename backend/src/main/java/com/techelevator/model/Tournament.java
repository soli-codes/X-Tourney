package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;

public class Tournament {
	private int tournamentId;
	private boolean isOpen;
	private String eliminationType; 
	private Date startDate;
	private Date endDate;
	private Date signUpOpen;
	private Date signUpClose;
	private boolean isPrivate;
	private String name;
	private String description;
	private int gameTypeId;
	private int maxTeamCount;
	
	

	public Tournament() {
	}

	public int getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public int getMaxTeamCount() {
		return maxTeamCount;
	}

	public void setMaxTeamCount(int maxTeamCount) {
		this.maxTeamCount = maxTeamCount;
	}

	public String getEliminationType() {
		return eliminationType;
	}

	public void setEliminationType(String eliminationType) {
		this.eliminationType = eliminationType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getSignUpOpen() {
		return signUpOpen;
	}

	public void setSignUpOpen(Date signUpOpen) {
		this.signUpOpen = signUpOpen;
	}

	public Date getSignUpClose() {
		return signUpClose;
	}

	public void setSignUpClose(Date signUpClose) {
		this.signUpClose = signUpClose;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGameTypeId() {
		return gameTypeId;
	}

	public void setGameTypeId(int gameTypeId) {
		this.gameTypeId = gameTypeId;
	}
	
	
}
