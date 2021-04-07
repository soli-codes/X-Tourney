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
import com.techelevator.dao.TournamentDAO;
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
	public Tournament getTournament(@PathVariable int id) {
		return dao.getTournamentById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/tournaments/create", method = RequestMethod.POST)
	public void create(@RequestBody Tournament tournament) {
		dao.createTournament(tournament);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/tournaments/{id}", method = RequestMethod.DELETE)
	public void deleteAccount(@PathVariable int id) {
		dao.deleteTournament(id);
	}
	
}
