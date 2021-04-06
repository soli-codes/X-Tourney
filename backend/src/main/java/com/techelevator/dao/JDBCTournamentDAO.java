package com.techelevator.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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
				+ "is_open, max_teams, elimination_type, start_date, end_date, signup_open, signup_close)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING tournament_id;";
		
		int tournamentId = jdbcTemplate.queryForObject(sqlInsertTournament, Integer.class, tournament.getGameTypeId(), tournament.getName(), 
				tournament.getDescription(), tournament.isPrivate(), tournament.isOpen(), tournament.getMaxTeamCount(), tournament.getEliminationType(),
				tournament.getStartDate(), tournament.getEndDate(), tournament.getSignUpOpen(), tournament.getSignUpClose());
		
		
		tournament.setTournamentId(tournamentId);
		
		return tournament;
	}

	//front end will need to handle this. User should not be able to delete the tournament after it has already started. (ADD UPDATE LATER)
	@Override
	public void deleteTournament(int tournamentId) {
		String sqlDeleteTournament = "DELETE FROM tournament WHERE tournament_id = ?;";
		
		jdbcTemplate.update(sqlDeleteTournament, tournamentId);
		
	}
	
	private Tournament mapTournamentFromRowSet(SqlRowSet rowset) {
		Tournament theTournament = new Tournament();
		
		int tournamentId = rowset.getInt("tournement_id");
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
		
		Date startDate = rowset.getDate("start_date");
		theTournament.setStartDate(startDate);
		
		Date endDate = rowset.getDate("end_date");
		theTournament.setEndDate(endDate);
		
		Date signUpOpen = rowset.getDate("signup_open");
		theTournament.setSignUpOpen(signUpOpen);
		
		Date signUpClose = rowset.getDate("signup_close");
		theTournament.setSignUpClose(signUpClose);
		
		return theTournament;
		
	}

}
