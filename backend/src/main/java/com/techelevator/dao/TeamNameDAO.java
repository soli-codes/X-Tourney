package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.TeamName;

public interface TeamNameDAO {
	
	List<TeamName> listAllTeamNames();
	
	List<TeamName> getTeamsByUserId(int userId);
	
	TeamName getTeamNameById(int id);
	
	TeamName createNewTeam(TeamName teamName);
	
	void updateTeam(TeamName teamName, int teamId);
	
	void deleteTeam(int id);
}
