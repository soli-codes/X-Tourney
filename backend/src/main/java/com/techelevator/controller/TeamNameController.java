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

import com.techelevator.dao.TeamNameDAO;
import com.techelevator.model.TeamName;
import com.techelevator.model.TeamNameUsers;


@RestController
@CrossOrigin 
@RequestMapping("")
public class TeamNameController {
	
	private TeamNameDAO dao;
	
	public TeamNameController(TeamNameDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(path = "/teamnames", method = RequestMethod.GET)
	public List<TeamName> list(){
		return dao.listAllTeamNames();
	}
	
	@RequestMapping(path = "/teamnames/user/{id}", method = RequestMethod.GET)
	public List<TeamName> getTeamsByUserId(@Valid @PathVariable int id) {
		return dao.getTeamsByUserId(id);
	}
	
	@RequestMapping(path = "/teamnames/{id}", method = RequestMethod.GET)
	public TeamName getTeamName(@Valid @PathVariable int id){
		return dao.getTeamNameById(id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/teamnames/create", method = RequestMethod.POST)
	public TeamName create(@Valid @RequestBody TeamName teamName) {
		return dao.createNewTeam(teamName);
	}
	
	@PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/teamnames/{id}/update", method = RequestMethod.PUT)
    public void executeRequest(@Valid @RequestBody TeamName teamName,@PathVariable int id ) {
    	dao.updateTeam(teamName, id);
    }
    
	@PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/teamnames/{id}", method = RequestMethod.DELETE)
	public void deleteTeamName(@Valid @PathVariable int id) {
		dao.deleteTeam(id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/teamnameusers/create", method = RequestMethod.POST)
	public boolean addUserToTeam(@Valid @RequestBody TeamNameUsers teamNameUser) {
		return dao.addUserToTeam(teamNameUser);
	}
}
