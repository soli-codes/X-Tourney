package com.techelevator.model;

import java.time.LocalDate;

public class Tournament {
	private int tournamentId;
	private boolean isOpen;
	private String eliminationType; 
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate signUpOpen;
	private LocalDate signUpClose;
	private boolean isPrivate;
	private String name;
	private String description;
	private int gameTypeId;
	
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

	public String getEliminationType() {
		return eliminationType;
	}

	public void setEliminationType(String eliminationType) {
		this.eliminationType = eliminationType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getSignUpOpen() {
		return signUpOpen;
	}

	public void setSignUpOpen(LocalDate signUpOpen) {
		this.signUpOpen = signUpOpen;
	}

	public LocalDate getSignUpClose() {
		return signUpClose;
	}

	public void setSignUpClose(LocalDate signUpClose) {
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
