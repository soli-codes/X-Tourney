package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.GameType;

@Component
public class JDBCGameTypeDAO implements GameTypeDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCGameTypeDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<GameType> listAllGameTypes() {
		String sqlListAllGameTypes = "SELECT * FROM game_type;";
		
		List<GameType> gameTypes = new ArrayList<>();
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlListAllGameTypes);
		
		while(rowSet.next()) {
			GameType theGameType = mapGameTypeFromRowSet(rowSet);
			
			gameTypes.add(theGameType);
		}
		
		return gameTypes;
	}

	@Override
	public GameType getGameTypeById(int gameTypeId) {
		String sqlGetGameTypeById = "SELECT * FROM game_type WHERE game_type_id = ?";
		
		GameType theGameType = new GameType();
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlGetGameTypeById, gameTypeId);
		
		while(rowSet.next()) {
			theGameType = mapGameTypeFromRowSet(rowSet);
		}
		
		return theGameType;
	}

	@Override
	public GameType createGameType(GameType gameType) {
		String sqlCreateNewGameType = "INSERT INTO game_type(game_type_id, game_type)"
									+ "VALUES(?,?) RETURNING game_type_id;";
		
		int gameTypeId = jdbcTemplate.queryForObject(sqlCreateNewGameType, Integer.class, gameType.getGameTypeId(), gameType.getGameType());
		gameType.setGameTypeId(gameTypeId);
		
		return gameType;
	}

	@Override
	public void deleteGameType(int gameTypeId) {
		String sqlDeleteGameType = "DELETE FROM game_type WHERE game_type_id = ?;";
		
		jdbcTemplate.update(sqlDeleteGameType, gameTypeId);
		
	}
	
	private GameType mapGameTypeFromRowSet(SqlRowSet rowset) {
		GameType theGameType = new GameType();
		
		int gameTypeId = rowset.getInt("game_type_id");
		theGameType.setGameTypeId(gameTypeId);
		
		String gameType = rowset.getString("game_type");
		theGameType.setGameType(gameType);
		
		return theGameType;
		
	}

}
