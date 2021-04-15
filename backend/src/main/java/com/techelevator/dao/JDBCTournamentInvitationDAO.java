package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.TournamnetInvitation;
import com.techelevator.model.Tournament;

@Component
public class JDBCTournamentInvitationDAO implements TournamentInvitationDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCTournamentInvitationDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public TournamnetInvitation createInvitation(TournamnetInvitation tournamnetInvitation) {
		
		String sqlInsertInvitation = "INSERT INTO tournament_invitation (tournament_id, team_id, has_accepted) VALUES (?,?,?);";
		
		jdbcTemplate.update(sqlInsertInvitation, tournamnetInvitation.getTournamentId(), tournamnetInvitation.getTeamId(), tournamnetInvitation.getInviteStatus());
		
		return tournamnetInvitation;
	}

	@Override
	public List<TournamnetInvitation> getPendingInvitations(int userId) {
		String sqlGetPendingTransactionsByTeamId = "SELECT tournament_id, tournament_invitation.team_id, has_accepted "
												+ "FROM tournament_invitation "
												+ "JOIN team_name ON team_name.team_id = tournament_invitation.team_id "
												+ "WHERE tournament_invitation.team_id IN "
												+ "(SELECT team_name.team_id FROM team_name JOIN team_name_users "
												+ "ON team_name.team_id = team_name_users.team_id "
												+ "JOIN users ON team_name_users.user_id = users.user_id "
												+ "WHERE users.user_id = ? AND has_accepted = 'pending');";
		
		List<TournamnetInvitation> pendingInvitations = new ArrayList<>();
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlGetPendingTransactionsByTeamId, userId);
		
		while(rowSet.next()) {
			TournamnetInvitation theInvitation = mapTournamentInvitationFromRowSet(rowSet);
			pendingInvitations.add(theInvitation);
		}
		
		return pendingInvitations;
		
	}

	@Override
	public void updateInvitationToAcceptedorDeclined(TournamnetInvitation tournamnetInvitation) {
		
		String sqlUpdateInvitationStatus = "UPDATE tournament_invitation SET has_accepted = ? WHERE tournament_id = ? AND team_id = ?;";
		
		jdbcTemplate.update(sqlUpdateInvitationStatus, tournamnetInvitation.getInviteStatus(), tournamnetInvitation.getTournamentId(), tournamnetInvitation.getTeamId());
		
	}
	
	private TournamnetInvitation mapTournamentInvitationFromRowSet(SqlRowSet rowset) {
		TournamnetInvitation theInvitation = new TournamnetInvitation();
		
		int tournamentId = rowset.getInt("tournament_id");
		theInvitation.setTournamentId(tournamentId);
		
		int teamId = rowset.getInt("team_id");
		theInvitation.setTeamId(teamId);
		
		String hasAccepted = rowset.getString("has_accepted");
		theInvitation.setInviteStatus(hasAccepted);
		
		return theInvitation;
	}
}
