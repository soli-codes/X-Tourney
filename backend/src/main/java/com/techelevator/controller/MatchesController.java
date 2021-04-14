package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import com.techelevator.model.TeamName;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
	public Matches getMatch(@Valid @PathVariable int id) {
		return dao.getMatchById(id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping( path = "/matches/create", method = RequestMethod.POST)
	public BracketInformation createMatch(@Valid @RequestBody BracketInformation bracketInfo) {
		return dao.createMatch(bracketInfo);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping( path = "/matches/update", method = RequestMethod.PUT)
	public void updateMatch(@Valid @RequestBody Matches match) {
		dao.updateMatch(match);
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping( path = "/matches/{id}", method = RequestMethod.DELETE)
	public void deleteMatch(@Valid @PathVariable int id) {
		dao.deleteMatch(id);
	}
	
}
