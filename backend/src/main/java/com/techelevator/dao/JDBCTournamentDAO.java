package com.techelevator.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Matches;
import com.techelevator.model.TeamName;
import com.techelevator.model.Tournament;
import com.techelevator.dao.TournamentDAO;

@Component
public class JDBCTournamentDAO implements TournamentDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCTournamentDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Tournament> listAllTournaments() {
		String sqlListAllTournaments = "SELECT * "
									+	"FROM tournament;";
		
		List<Tournament> tournaments = new ArrayList<>();
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlListAllTournaments);
		
		while(rowSet.next()) {
			Tournament theTournament = mapTournamentFromRowSet(rowSet);
			tournaments.add(theTournament);
		}
		
		return tournaments;
	}

	@Override
	public Tournament getTournamentById(int tournamentId) {
		String sqlGetTournamentById = "SELECT * FROM tournament WHERE tournament_id = ?";
		
		Tournament theTournament = new Tournament();
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlGetTournamentById, tournamentId);
		
		while(rowSet.next()) {
			theTournament = mapTournamentFromRowSet(rowSet);
		}
		return theTournament;
	}

	@Override
	public Tournament createTournament(Tournament tournament) {
		String sqlInsertTournament = "INSERT INTO tournament (game_type_id, name, description, is_private, "
				+ "is_open, max_teams, elimination_type, start_date, end_date, signup_open, signup_close,"
				+ "host_id, start_time, tournament_image)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING tournament_id;";
		
		int tournamentId = jdbcTemplate.queryForObject(sqlInsertTournament, Integer.class, tournament.getGameTypeId(), tournament.getName(), 
				tournament.getDescription(), tournament.isPrivate(), tournament.isOpen(), tournament.getMaxTeamCount(), tournament.getEliminationType(),
				tournament.getStartDate(), tournament.getEndDate(), tournament.getSignUpOpen(), tournament.getSignUpClose(), tournament.getHostId(),
				tournament.getStartTime(), tournament.getTournamentImage());
		
		
		tournament.setTournamentId(tournamentId);
		
		return tournament;
	}

	//front end will need to handle this. User should not be able to delete the tournament after it has already started. (ADD UPDATE LATER)
	@Override
	public void deleteTournament(int tournamentId) {
		String sqlDeleteTournament = "DELETE FROM tournament WHERE tournament_id = ?;";
		
		jdbcTemplate.update(sqlDeleteTournament, tournamentId);
		
	}
	
	@Override
	public List<TeamName> getTeamsByTournamentId(int tournamentId){
		
		String sqlGetTeamsByTournamentId = "SELECT * FROM team_name JOIN tournament_teams ON team_name.team_id = tournament_teams.team_id JOIN tournament ON tournament_teams.tournament_id = tournament.tournament_id WHERE tournament.tournament_id = ?;";

		List<TeamName> teams = new ArrayList<>();

		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlGetTeamsByTournamentId, tournamentId);

		while(rowSet.next()) {
			TeamName theTeamName = mapTeamNameFromRowSet(rowSet);
			teams.add(theTeamName);
		}
		
		return teams;
	}
	
	@Override
	public List<List<Matches>> getMatchesBytournamentId(int tournamentId){
		
		String sqlGetMatchesByTournamentId = "SELECT * FROM matches WHERE tournament_id = ?;";
		
		List<Matches> matches = new ArrayList<>();
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlGetMatchesByTournamentId, tournamentId);
		
		while(rowSet.next()) {
			Matches theMatch = mapMatchFromRowSet(rowSet);
			matches.add(theMatch);
		}
		// NEED TO SORT MATCHES BY MATCH ID HERE
		matches.sort(Comparator.comparing(Matches::getMatchId));
		List<List<Matches>> fullList = new ArrayList<>();
		
		String sqlGetTournamentSize = "SELECT max_teams FROM tournament WHERE tournament_id = ?;";
		
		int maxTeams = jdbcTemplate.queryForObject(sqlGetTournamentSize, Integer.class, tournamentId);
		int j = 0;
		while(maxTeams/2 >= 1) {
			List<Matches> tempList = new ArrayList<>();
			for(int i = 0; i < maxTeams/2; i++) {
				tempList.add(matches.get(j));
				j++;
			}
			fullList.add(tempList);
			maxTeams = maxTeams/2;
		}
		
	
		return fullList;
	}
	
	@Override
	public List<Tournament> getTournamentsByHostId(int hostId) {
		
			String sqlListAllTournaments = "SELECT * FROM tournament WHERE host_id = ?;";
			
			List<Tournament> tournaments = new ArrayList<>();
			
			SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlListAllTournaments, hostId);
			
			while(rowSet.next()) {
			Tournament theTournament = mapTournamentFromRowSet(rowSet);
			tournaments.add(theTournament);
			}

			return tournaments;
	}
	
	@Override
	public List<Tournament> getTournamentsByUserId(int userId) {
		
		String sqlListAllTournamentsByUserId = "SELECT tournament.tournament_id, game_type_id, host_id, tournament_winner_id, name, " + 
				"description, has_started, is_private, is_open, max_teams, elimination_type, " + 
				"start_date, start_time, end_date, signup_open, signup_close, tournament_image " + 
				"FROM tournament JOIN tournament_teams ON tournament.tournament_id = tournament_teams.tournament_id " + 
				"JOIN team_name_users ON tournament_teams.team_id = team_name_users.team_id " + 
				"WHERE team_name_users.user_id = ? GROUP BY tournament.tournament_id;";
		
		List<Tournament> tournaments = new ArrayList<>();
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlListAllTournamentsByUserId, userId);
		
		while(rowSet.next()) {
		Tournament theTournament = mapTournamentFromRowSet(rowSet);
		tournaments.add(theTournament);
		}

		return tournaments;

	}
	
	@Override
	public void addTeamToTournament(TeamName team, int tournamentId) {
		
		String sqlAddTeamToTournament = "INSERT INTO tournament_teams (tournament_id, team_id) VALUES (?, ?);";
		
		jdbcTemplate.update(sqlAddTeamToTournament, tournamentId, team.getTeamId());
	
	}
	
	@Override
	public List<TeamName> getAllTeamsThatAreNotInTournament(int tournamentId) {
		
		String sqlGetAllTeamsNotInTournament = "SELECT team_name.team_id, team_name, wins, losses, tournament_wins, tournaments_entered, team_image "
											+ "FROM team_name "
											+ "JOIN tournament_teams "
											+ "ON team_name.team_id = tournament_teams.team_id WHERE team_name.team_id "
											+ "NOT IN "
											+ "(SELECT team_name.team_id FROM team_name "
											+ "JOIN tournament_teams "
											+ "ON team_name.team_id = tournament_teams.team_id "
											+ "JOIN tournament "
											+ "ON tournament_teams.tournament_id = tournament.tournament_id WHERE tournament.tournament_id = ?)"
											+ "GROUP BY team_name.team_id;";
		
		List<TeamName> teams = new ArrayList<>();

		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlGetAllTeamsNotInTournament, tournamentId);

		while(rowSet.next()) {
			TeamName theTeamName = mapTeamNameFromRowSet(rowSet);
			teams.add(theTeamName);
		}
		
		return teams;
	}


	
	private Tournament mapTournamentFromRowSet(SqlRowSet rowset) {
		Tournament theTournament = new Tournament();
		
		int tournamentId = rowset.getInt("tournament_id");
		theTournament.setTournamentId(tournamentId);
		
		int gameTypeId = rowset.getInt("game_type_id");
		theTournament.setGameTypeId(gameTypeId);
		
		String name = rowset.getString("name");
		theTournament.setName(name);
		
		String description = rowset.getString("description");
		theTournament.setDescription(description);
		
		boolean isPrivate = rowset.getBoolean("is_private");
		theTournament.setPrivate(isPrivate);
		
		boolean isOpen = rowset.getBoolean("is_open");
		theTournament.setOpen(isOpen);
		
		int maxTeams = rowset.getInt("max_teams");
		theTournament.setMaxTeamCount(maxTeams);
		
		String eliminationType = rowset.getString("elimination_type");
		theTournament.setEliminationType(eliminationType);
		
		String startDate = rowset.getString("start_date");
		theTournament.setStartDate(startDate);
		
		String endDate = rowset.getString("end_date");
		theTournament.setEndDate(endDate);
		
		String signUpOpen = rowset.getString("signup_open");
		theTournament.setSignUpOpen(signUpOpen);
		
		String signUpClose = rowset.getString("signup_close");
		theTournament.setSignUpClose(signUpClose);
		
		String startTime = rowset.getString("start_time");
		theTournament.setStartTime(startTime);
		
		int hostId = rowset.getInt("host_id");
		theTournament.setHostId(hostId);
		
		String tournamentImage = rowset.getString("tournament_image");
		theTournament.setTournamentImage(tournamentImage);
		
		int tournamentWinnderId = rowset.getInt("tournament_winner_id");
		theTournament.setTournamentWinnerId(tournamentWinnderId);
		
		boolean hasStarted = rowset.getBoolean("has_started");
		theTournament.setHasStarted(hasStarted);
		
		return theTournament;
		
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

	private Matches mapMatchFromRowSet(SqlRowSet rs) {
		Matches match = new Matches();
	
		match.setMatchId(rs.getInt("match_id"));
		match.setTournamentId(rs.getInt("tournament_id"));
		match.setTeamOneId(rs.getInt("team_1_id"));
		match.setTeamTwoId(rs.getInt("team_2_id"));
		match.setWinningTeamId(rs.getInt("winning_team_id"));
		match.setLosingTeamId(rs.getInt("losing_team_id"));
		match.setWinningTeamScore(rs.getInt("winning_team_score"));
		match.setLosingTeamScore(rs.getInt("losing_team_score"));
		match.setMatchDate(rs.getString("match_date"));
		match.setMatchTime(rs.getString("match_time"));
		
	return match;
	}

	

	

	
	
	

}
