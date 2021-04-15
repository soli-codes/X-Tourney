package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.TeamInvitation;


@Component
public class JDBCTeamInvitationDAO implements TeamInvitationDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCTeamInvitationDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public TeamInvitation createInvitation(TeamInvitation teamInvitation) {
		
		String sqlInsertInvitation = "INSERT INTO team_invitation (user_id, team_id, has_accepted) VALUES (?,?,?);";
		
		jdbcTemplate.update(sqlInsertInvitation, teamInvitation.getUserId(), teamInvitation.getTeamId(), teamInvitation.getInviteStatus());
		
		return teamInvitation;
	}

	@Override
	public List<TeamInvitation> getPendingInvitations(int teamId) {
		String sqlGetPendingTransactionsByTeamId = "SELECT team_invitation.user_id, team_invitation.team_id, team_invitation.has_accepted "
												+ "FROM team_invitation "
												+ "WHERE team_invitation.team_id = ? AND team_invitation.has_accepted = 'pending';"; 

		List<TeamInvitation> pendingInvitations = new ArrayList<>();

		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlGetPendingTransactionsByTeamId, teamId);

		while(rowSet.next()) {
		TeamInvitation theInvitation = mapTeamInvitationFromRowSet(rowSet);
		pendingInvitations.add(theInvitation);
		}

		return pendingInvitations;
	}

	@Override
	public void updateInvitationToAcceptedorDeclined(TeamInvitation teamInvitation) {
		
		String sqlUpdateInvitationStatus = "UPDATE team_invitation SET has_accepted = ? WHERE user_id = ? AND team_id = ?;";
		
		jdbcTemplate.update(sqlUpdateInvitationStatus, teamInvitation.getInviteStatus(), teamInvitation.getUserId(), teamInvitation.getTeamId());
		
	}
	
	private TeamInvitation mapTeamInvitationFromRowSet(SqlRowSet rowset) {
		TeamInvitation theInvitation = new TeamInvitation();
		
		int userId = rowset.getInt("user_id");
		theInvitation.setUserId(userId);
		
		int teamId = rowset.getInt("team_id");
		theInvitation.setTeamId(teamId);
		
		String hasAccepted = rowset.getString("has_accepted");
		theInvitation.setInviteStatus(hasAccepted);
		
		return theInvitation;
	}

}
