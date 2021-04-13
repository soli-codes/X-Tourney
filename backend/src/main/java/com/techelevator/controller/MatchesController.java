package com.techelevator.controller;

import java.util.List;

import com.techelevator.model.TeamName;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.MatchesDAO;
import com.techelevator.model.Matches;
import com.techelevator.model.BracketInformation;

@RestController
@CrossOrigin
@RequestMapping("")
public class MatchesController {

	private MatchesDAO dao;
	
	public MatchesController(MatchesDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping( path = "/matches", method = RequestMethod.GET)
	public List<Matches> list() {
		return dao.getAllMatches();
	}
	
	@RequestMapping( path = "/matches/{id}", method = RequestMethod.GET)
	public Matches getMatch(@PathVariable int id) {
		return dao.getMatchById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping( path = "/matches/create", method = RequestMethod.POST)
	public BracketInformation createMatch(@RequestBody BracketInformation bracketInfo) {
		return dao.createMatch(bracketInfo);
	}
	
	@RequestMapping( path = "/matches/update", method = RequestMethod.PUT)
	public void updateMatch(@RequestBody Matches match, int nextMatchId) {
		dao.updateMatch(match, nextMatchId);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping( path = "/matches/{id}", method = RequestMethod.DELETE)
	public void deleteMatch(@PathVariable int id) {
		dao.deleteMatch(id);
	}
	
}
