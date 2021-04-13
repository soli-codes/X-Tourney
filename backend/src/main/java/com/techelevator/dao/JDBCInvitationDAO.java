package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Invitation;
import com.techelevator.model.Tournament;

@Component
public class JDBCInvitationDAO implements InvitationDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCInvitationDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Invitation createInvitation(Invitation invitation) {
		
		String sqlInsertInvitation = "INSERT INTO invitation (tournament_id, team_id, has_accepted) VALUES (?,?,?);";
		
		jdbcTemplate.update(sqlInsertInvitation, invitation.getTournamentId(), invitation.getTeamId(), invitation.getInviteStatus());
		
		return invitation;
	}

	@Override
	public List<Invitation> getPendingInvitations(int teamId) {
		String sqlGetPendingTransactionsByTeamId = "SELECT tournament_id, invitation.team_id, has_accepted "
												+ "FROM invitation "
												+ "JOIN team_name ON team_name.team_id = invitation.team_id "
												+ "WHERE invitation.team_id = ?;";
		
		List<Invitation> pendingInvitations = new ArrayList<>();
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlGetPendingTransactionsByTeamId, teamId);
		
		while(rowSet.next()) {
			Invitation theInvitation = mapInvitationFromRowSet(rowSet);
			pendingInvitations.add(theInvitation);
		}
		
		return pendingInvitations;
		
	}

	@Override
	public void updateInvitationToAcceptedorDeclined(Invitation invitation) {
		
		String sqlUpdateInvitationStatus = "UPDATE invitation SET has_accepted = ? WHERE tournament_id = ? AND team_id = ?;";
		
		jdbcTemplate.update(sqlUpdateInvitationStatus, invitation.getInviteStatus(), invitation.getTournamentId(), invitation.getTeamId());
		
	}
	
	private Invitation mapInvitationFromRowSet(SqlRowSet rowset) {
		Invitation theInvitation = new Invitation();
		
		int tournamentId = rowset.getInt("tournament_id");
		theInvitation.setTournamentId(tournamentId);
		
		int teamId = rowset.getInt("team_id");
		theInvitation.setTeamId(teamId);
		
		String hasAccepted = rowset.getString("has_accepted");
		theInvitation.setInviteStatus(hasAccepted);
		
		return theInvitation;
	}
}
