package com.techelevator.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Tournament {
	
//	@NotNull(message = "Required Field")
//	@Positive(message = "Must be a positive numeric value")
	private int tournamentId;
	
	private boolean isOpen;
	
	@NotNull(message = "Required Field")
	private String eliminationType; 
	
	@NotNull (message = "Required Field")
	private String startDate;
	
	@NotNull(message = "Required Field")
	private String endDate;
	
	@NotNull(message = "Required Field")
	private String signUpOpen;
	
	@NotNull(message = "Required Field")
	private String signUpClose;
	
	
	private boolean isPrivate;
	
	@NotNull(message = "Required Field")
	private String name;
	
	
	private String description;
	
	@NotNull(message = "Required Field")
	@Positive (message = "Must be a positive numeric value")
	private int gameTypeId;
	
	@NotNull(message = "Required Field")
	@Positive (message = "Must be a positive numeric value")
	private int maxTeamCount;
	
	@NotNull(message = "Required Field")
	@Positive (message = "Must be a positive numeric value")
	private int hostId;
	
	@NotNull(message = "Required Field")
	private String startTime;
	
	@NotNull(message = "Required Field")
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
