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

import com.techelevator.dao.InvitationDAO;
import com.techelevator.model.Invitation;

@RestController
@CrossOrigin 
@RequestMapping("")
public class InvitationController {
	
private InvitationDAO dao;
	
	public InvitationController(InvitationDAO dao) {
		this.dao = dao;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "/users/{id}/invitations", method = RequestMethod.GET)
	public List<Invitation> listPendingInvitations(@Valid @PathVariable int id){
		return dao.getPendingInvitations(id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/teamnames/invite", method = RequestMethod.POST)
	public void create(@Valid @RequestBody Invitation invitation) {
		dao.createInvitation(invitation);
	}
	
	@PreAuthorize("isAuthenticated()")
	 @RequestMapping(path = "/teamnames/{id}/updateInvite", method = RequestMethod.PUT)
	    public void executeUpdate(@Valid @RequestBody Invitation invitation, @PathVariable int id ) {
	    	dao.updateInvitationToAcceptedorDeclined(invitation);
	    }
	
	
	
	
	
}
