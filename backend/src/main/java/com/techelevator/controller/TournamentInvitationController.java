package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.TournamentInvitationDAO;
import com.techelevator.model.TournamnetInvitation;

@RestController
@CrossOrigin 
@RequestMapping("")
public class TournamentInvitationController {
	
private TournamentInvitationDAO dao;
	
	public TournamentInvitationController(TournamentInvitationDAO dao) {
		this.dao = dao;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "/users/{id}/tournament-invitations", method = RequestMethod.GET)
	public List<TournamnetInvitation> listPendingInvitations(@Valid @PathVariable int id){
		return dao.getPendingInvitations(id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/teamnames/tournament-invite", method = RequestMethod.POST)
	public void create(@Valid @RequestBody TournamnetInvitation tournamnetInvitation) {
		dao.createInvitation(tournamnetInvitation);
	}
	
	@PreAuthorize("isAuthenticated()")
	 @RequestMapping(path = "/teamnames/{id}/update/tournament-invite", method = RequestMethod.PUT)
	    public void executeUpdate(@Valid @RequestBody TournamnetInvitation tournamnetInvitation, @PathVariable int id ) {
	    	dao.updateInvitationToAcceptedorDeclined(tournamnetInvitation);
	    }
	
	
	
	
	
}
