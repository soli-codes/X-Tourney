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
import com.techelevator.dao.TournamentDAO;
import com.techelevator.model.Matches;
import com.techelevator.model.TeamName;
import com.techelevator.model.Tournament;

@RestController
@CrossOrigin
@RequestMapping("")
public class TournamentController {
	
	private TournamentDAO dao;
	
	public TournamentController(TournamentDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping( path = "/tournaments", method = RequestMethod.GET)
	public List<Tournament> list(){
		return dao.listAllTournaments();
	}
	
	@RequestMapping(path = "/tournaments/{id}", method = RequestMethod.GET)
	public Tournament getTournament(@Valid @PathVariable int id) {
		return dao.getTournamentById(id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/tournaments/create", method = RequestMethod.POST)
	public void create(@Valid @RequestBody Tournament tournament) {
		dao.createTournament(tournament);
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/tournaments/{id}", method = RequestMethod.DELETE)
	public void deleteAccount(@PathVariable int id) {
		dao.deleteTournament(id);
	}
	
	@RequestMapping( path = "/tournaments/{id}/teams", method = RequestMethod.GET)
	public List<TeamName> listTeamsByTournament(@Valid @PathVariable int id){
		return dao.getTeamsByTournamentId(id);
	}
	
	@RequestMapping( path = "/tournaments/{id}/matches", method = RequestMethod.GET)
	public List<List<Matches>> listMatchesByTournament(@Valid @PathVariable int id){
		return dao.getMatchesBytournamentId(id);
	}
	
	@RequestMapping( path = "/tournaments/host/{id}", method = RequestMethod.GET)
	public List<Tournament> listTournamentsByHostId(@Valid @PathVariable int id){
		return dao.getTournamentsByHostId(id);
	}
	
	@RequestMapping( path = "/tournaments/user/{id}", method = RequestMethod.GET)
	public List<Tournament> listTournamentsByUserId(@PathVariable int id){
		return dao.getTournamentsByUserId(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/tournaments/{id}/create", method = RequestMethod.POST)
	public void addTeamToTournament(@RequestBody TeamName team, @PathVariable int id) {
		dao.addTeamToTournament(team, id);
	}
	
	@RequestMapping( path = "/tournaments/{id}/invitableteams", method = RequestMethod.GET)
	public List<TeamName> listTeamsWhoAreNotInTournament(@Valid @PathVariable int id){
		return dao.getAllTeamsThatAreNotInTournament(id);
	}
	
}
