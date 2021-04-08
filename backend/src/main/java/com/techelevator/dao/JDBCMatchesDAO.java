package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

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
	public Matches createMatch(Matches match) {
		String sqlCreateMatch = "INSERT INTO matches (tournament_id, team_1_id, team_2_id) "
				+ "VALUES (?, ?, ?) RETURNING match_id;";
		
		int matchId = jdbcTemplate.queryForObject(sqlCreateMatch, Integer.class, 
				match.getTournamentId(), match.getTeamOneId(), match.getTeamTwoId());
		
		match.setMatchId(matchId);
		
		return match;
	}

	@Override
	public void updateMatch(Matches match) {
		String sqlUpdateMatch = "UPDATE matches SET winning_team_id = ?, losing_team_id = ?, "
				+ "winning_team_score = ?, losing_team_score = ?, match_date = ?, match_time = ?"
				+ "WHERE match_id = ?;";
		
		jdbcTemplate.update(sqlUpdateMatch, match.getWinningTeamId(), match.getLosingTeamId(), 
				match.getWinningTeamScore(), match.getLosingTeamScore(), match.getMatchDate(), 
				match.getMatchTime(), match.getMatchId());
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
		
		return match;
	}

}
