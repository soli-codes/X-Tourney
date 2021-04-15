package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.TournamnetInvitation;

public interface TournamentInvitationDAO {
	
	TournamnetInvitation createInvitation(TournamnetInvitation tournamnetInvitation);
	
	List<TournamnetInvitation> getPendingInvitations(int teamId);
	
	void updateInvitationToAcceptedorDeclined(TournamnetInvitation tournamnetInvitation);
}
