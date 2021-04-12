package com.techelevator.model;

public class Tournament {
	private int tournamentId;
	private boolean isOpen;
	private String eliminationType; 
	private String startDate;
	private String endDate;
	private String signUpOpen;
	private String signUpClose;
	private boolean isPrivate;
	private String name;
	private String description;
	private int gameTypeId;
	private int maxTeamCount;
	private int hostId;
	private String startTime;
	private String tournamentImage;
	private int tournamentWinnerId;
	private boolean hasStarted;
	

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSignUpOpen() {
		return signUpOpen;
	}

	public void setSignUpOpen(String signUpOpen) {
		this.signUpOpen = signUpOpen;
	}

	public String getSignUpClose() {
		return signUpClose;
	}

	public void setSignUpClose(String signUpClose) {
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
	
	public int getHostId() {
		return hostId;
	}
	
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTournamentImage() {
		return tournamentImage;
	}
	
	public void setTournamentImage(String tournamentImage) {
		this.tournamentImage = tournamentImage;
	}
	
	public int getTournamentWinnerId() {
		return tournamentWinnerId;
	}
	
	public void setTournamentWinnerId(int tournamentWinnerId) {
		this.tournamentWinnerId = tournamentWinnerId;
	}
	
	public boolean getHasStarted() {
		return hasStarted;
	}
	
	public void setHasStarted(boolean hasStarted) {
		this.hasStarted = hasStarted;
	}
	
}
