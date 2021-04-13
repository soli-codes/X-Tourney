package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Invitation;

public interface InvitationDAO {
	
	Invitation createInvitation(Invitation invitation);
	
	List<Invitation> getPendingInvitations(int teamId);
	
	void updateInvitationToAcceptedorDeclined(Invitation invitation);
}
