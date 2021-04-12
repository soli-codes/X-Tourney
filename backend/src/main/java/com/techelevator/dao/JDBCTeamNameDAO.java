package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.TeamName;

@Component
public class JDBCTeamNameDAO implements TeamNameDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCTeamNameDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<TeamName> listAllTeamNames() {
		String sqlGetAllTeams = "SELECT * FROM team_name;";
		
		List<TeamName> teamNames = new ArrayList<>();
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlGetAllTeams);
		
		while(rowSet.next()) {
			TeamName theTeamName = mapTeamNameFromRowSet(rowSet);
			teamNames.add(theTeamName);
		} return teamNames;
	} 

	@Override
	public TeamName getTeamNameById(int id) {
		String sqlGetTeambyId = "SELECT * FROM team_name WHERE team_id = ?;";
		
		TeamName theTeamName = new TeamName();
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlGetTeambyId, id);
		
		while(rowSet.next()) {
			theTeamName = mapTeamNameFromRowSet(rowSet);
		} return theTeamName;
	}

	@Override
	public TeamName createNewTeam(TeamName teamName) {
		String sqlInsertTeam = "INSERT INTO team_name (team_name) VALUES (?) RETURNING team_id;";
		
		int teamId = jdbcTemplate.queryForObject(sqlInsertTeam, Integer.class, teamName.getTeamName());
		
		teamName.setTeamId(teamId);
		
		return teamName;
	}

	@Override
	public void updateTeam(TeamName teamName, int teamId) {
		String sqlUpdateTeam = "UPDATE team_name SET "
								+ "team_name = ?, "
								+ "wins = ?, "
								+ "losses = ?, "
								+ "tournament_wins = ?, "
								+ "tournaments_entered = ? "
								+ "WHERE team_id = ?;";
		jdbcTemplate.update(sqlUpdateTeam, 
								teamName.getTeamName(), 
								teamName.getWins(), 
								teamName.getLosses(), 
								teamName.getTournamentWins(), 
								teamName.getTournamentsEntered(), 
								teamId);
		
	}

	@Override
	public void deleteTeam(int id) {
		String sqlDeleteTeam = "DELETE FROM team_name WHERE team_id = ?;";
		
		jdbcTemplate.update(sqlDeleteTeam, id);
		
	}
	
	private TeamName mapTeamNameFromRowSet(SqlRowSet rowset) {
		
		TeamName theTeamName = new TeamName();
		
		int teamNameId = rowset.getInt("team_id");
		theTeamName.setTeamId(teamNameId);
		
		String teamName = rowset.getString("team_name");
		theTeamName.setTeamName(teamName);
		
		int wins = rowset.getInt("wins");
		theTeamName.setWins(wins);
		
		int losses = rowset.getInt("losses");
		theTeamName.setLosses(losses);
		
		int tournamentWins = rowset.getInt("tournament_wins");
		theTeamName.setTournamentWins(tournamentWins);
		
		int tournamentsEntered = rowset.getInt("tournaments_entered");
		theTeamName.setTournamentsEntered(tournamentsEntered);
		
		String getTeamImage = rowset.getString("team_image");
		theTeamName.setTeamImage(getTeamImage);
		
		return theTeamName;
	}

}