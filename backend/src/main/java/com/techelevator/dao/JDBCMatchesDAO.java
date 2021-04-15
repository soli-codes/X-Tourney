package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.BracketInformation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Matches;

@Component
public class JDBCMatchesDAO implements MatchesDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCMatchesDAO (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Matches> getAllMatches() {
		String sqlGetAllMatches = "SELECT * FROM matches;";
		
		List<Matches> allMatches = new ArrayList<>();
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlGetAllMatches);
		
		while (rowSet.next()) {
			Matches match = mapMatchFromRowSet(rowSet);
			allMatches.add(match);
		}
		return allMatches;
	}

	@Override
	public Matches getMatchById(int matchId) {
		String sqlGetMatchById = "SELECT * FROM matches WHERE match_id = ?;";
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlGetMatchById, matchId);
		
		Matches match = new Matches();
		
		while (rowSet.next()) {
			match = mapMatchFromRowSet(rowSet);
		}
		return match;
	}

	@Override
	public BracketInformation createMatch(BracketInformation bracketInformation) {
		String sqlCreateMatch = "INSERT INTO matches (match_id, tournament_id, team_1_id, team_2_id) "
				+ "VALUES (?, ?, ?, ?) ";
		int tournamentSize = bracketInformation.getTournamentSize();
		int lowSeed = bracketInformation.getTournamentSize()-1;
		int highSeed = 0;
		for (int i = 1; i < tournamentSize; i++) {
			if (i <= tournamentSize/2) {
				jdbcTemplate.update(sqlCreateMatch, i, bracketInformation.getTournamentId(), bracketInformation.getTeams().get(highSeed), bracketInformation.getTeams().get(lowSeed));
				highSeed++;
				lowSeed--;
			}
			else jdbcTemplate.update(sqlCreateMatch, i, bracketInformation.getTournamentId(), 1, 1);
		}
		jdbcTemplate.update("UPDATE tournament SET has_started = true WHERE tournament_id = ?", bracketInformation.getTournamentId());
		return bracketInformation;
	}

	@Override
	public void updateMatch(Matches match) {
		String sqlUpdateMatch = "UPDATE matches SET winning_team_id = ?, losing_team_id = ?, "
				+ "winning_team_score = ?, losing_team_score = ?, match_date = ?, match_time = ?"
				+ "WHERE match_id = ? AND tournament_id = ?;";
		String sqlNextMatch;
		if (match.getMatchId() % 2 != 0) {
			sqlNextMatch = "UPDATE matches SET team_1_id = ? WHERE match_id = ? AND tournament_id = ?;";
		} else sqlNextMatch = "UPDATE matches SET team_2_id = ? WHERE match_id = ? AND tournament_id = ?;";;

		jdbcTemplate.update(sqlUpdateMatch, match.getWinningTeamId(), match.getLosingTeamId(), 
				match.getWinningTeamScore(), match.getLosingTeamScore(), match.getMatchDate(), 
				match.getMatchTime(), match.getMatchId(), match.getTournamentId());
		jdbcTemplate.update(sqlNextMatch, match.getWinningTeamId(), match.getNextMatch(), match.getTournamentId());
	}

	@Override
	public void deleteMatch(int matchId) {
		String sqlDeleteMatch = "DELETE FROM matches WHERE match_id = ?;";
		
		jdbcTemplate.update(sqlDeleteMatch, matchId);
	}
	
	//I'm not sure if this will cause an error/null values if it tries to map values that
	//are not in the table (winning team, losing team, etc)
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
		match.setNextMatch(rs.getInt("next_match"));
		
		return match;
	}

}
