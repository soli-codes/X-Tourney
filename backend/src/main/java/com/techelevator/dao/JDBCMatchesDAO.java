package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Matches;

public class JDBCMatchesDAO implements MatchesDAO {

	@Override
	public List<Matches> getAllMatches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matches getMatchById(int matchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matches createMatch(Matches match) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMatch(Matches Match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMatch(int matchId) {
		// TODO Auto-generated method stub
		
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
