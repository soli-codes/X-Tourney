package com.techelevator.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Tournament;

@Component
public class JDBCTournamentDAO implements TournamnetDAO {
	
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
			Tournament theTournament = mapTournementFromRowSet(rowSet);
			tournaments.add(theTournament);
		}
		
		
		return tournaments;
	}

	@Override
	public Tournament getTournamentById(int tournamentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tournament createTournament(Tournament tournament) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void deleteTournament(int tournamentId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Tournament mapTournementFromRowSet(SqlRowSet rowset) {
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
