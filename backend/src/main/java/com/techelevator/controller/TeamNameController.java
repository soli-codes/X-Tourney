package com.techelevator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.TeamNameDAO;
import com.techelevator.model.TeamName;


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
	public List<TeamName> getTeamsByUserId(@PathVariable int id) {
		return dao.getTeamsByUserId(id);
	}
	
	@RequestMapping(path = "/teamnames/{id}", method = RequestMethod.GET)
	public TeamName getTeamName(@PathVariable int id){
		return dao.getTeamNameById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/teamnames/create", method = RequestMethod.POST)
	public void create(@RequestBody TeamName teamName) {
		dao.createNewTeam(teamName);
	}
	
    @RequestMapping(path = "/teamnames/{id}/update", method = RequestMethod.PUT)
    public void executeRequest(@RequestBody TeamName teamName,@PathVariable int id ) {
    	dao.updateTeam(teamName, id);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/teamnames/{id}", method = RequestMethod.DELETE)
	public void deleteTeamName(@PathVariable int id) {
		dao.deleteTeam(id);
	}
	
}
