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

import com.techelevator.dao.TeamInvitationDAO;
import com.techelevator.model.TeamInvitation;

@RestController
@CrossOrigin 
@RequestMapping("")
public class TeamInvitationController {

private TeamInvitationDAO dao;
	
	public TeamInvitationController(TeamInvitationDAO dao) {
		this.dao = dao;
	}
	
	//@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "/teamnames/{id}/team-invitations", method = RequestMethod.GET)
	public List<TeamInvitation> listPendingInvitations(@Valid @PathVariable int id){
		return dao.getPendingInvitations(id);
	}
	
	//@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/teamnames/team-invite", method = RequestMethod.POST)
	public void create(@Valid @RequestBody TeamInvitation teamInvitation) {
		dao.createInvitation(teamInvitation);
	}
	
	//@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "/teamnames/{id}/update/team-invite", method = RequestMethod.PUT)
	    public void executeUpdate(@Valid @RequestBody TeamInvitation teamInvitation, @PathVariable int id ) {
	    	dao.updateInvitationToAcceptedorDeclined(teamInvitation);
	    }
	
}
