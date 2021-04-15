package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.TeamInvitation;



public interface TeamInvitationDAO {
	
	TeamInvitation createInvitation(TeamInvitation teamInvitation);
	
	List<TeamInvitation> getPendingInvitations(int teamId);
	
	void updateInvitationToAcceptedorDeclined(TeamInvitation teamInvitation);
	
}
