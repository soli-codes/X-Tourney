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

import com.techelevator.dao.GameTypeDAO;
import com.techelevator.model.GameType;

@RestController
@CrossOrigin 
@RequestMapping("")
public class GameTypeController {
	
	private GameTypeDAO dao;
	
	public GameTypeController(GameTypeDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(path = "/gametypes", method = RequestMethod.GET)
	public List<GameType> list(){
		return dao.listAllGameTypes();
	}
	
	@RequestMapping(path = "/gametypes/{id}", method = RequestMethod.GET)
	public GameType getGameType(@PathVariable int id){
		return dao.getGameTypeById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/gametypes/create", method = RequestMethod.POST)
	public void create(@RequestBody GameType gameType) {
		dao.createGameType(gameType);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/gametypes/{id}", method = RequestMethod.DELETE)
	public void deleteGameType(@PathVariable int id) {
		dao.deleteGameType(id);
	}
	
	
	
	
	
	
}
