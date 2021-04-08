package com.techelevator.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.GameType;


public class JDBCGameTypeDAOIntegrationTest extends DAOIntegrationTest {
		
	private GameTypeDAO gameTypeDao;
	private JdbcTemplate jdbcTemplate;
	private GameType test1;
	
	@Before
	public void setup() {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		gameTypeDao = new JDBCGameTypeDAO(jdbcTemplate);
		
		test1 = new GameType();
		test1.setGameType("Test");
		test1.setGameTypeId(500);
		
	}
	
	@Test
	public void createGameType_properly_add_gameType_to_DataBase() {
		//Arrange
		List<GameType> priorToCreate = new ArrayList<>();
		List<GameType> afterCreate = new ArrayList<>();
		//Act
		priorToCreate = gameTypeDao.listAllGameTypes();
		gameTypeDao.createGameType(test1);
		afterCreate = gameTypeDao.listAllGameTypes();
		//Assert
		Assert.assertEquals(priorToCreate.size() + 1, afterCreate.size());
	}
	
	@Test
	public void getGameTypeById_returns_right_gameType() {
		//Arrange
		String testName = test1.getGameType();
		GameType receivedName = new GameType();
		gameTypeDao.createGameType(test1);
		//Act
		receivedName = gameTypeDao.getGameTypeById(500);
		String actual = receivedName.getGameType();
		//Assert
		Assert.assertEquals(testName, actual);
	}
	
	@Test
	public void deleteGameType_deletes_from_database() {
		//Arrange
		List<GameType> priorToDelete = new ArrayList<>();
		List<GameType> afterDelete = new ArrayList<>();
		gameTypeDao.createGameType(test1);
		//Act
		priorToDelete = gameTypeDao.listAllGameTypes();
		gameTypeDao.deleteGameType(test1.getGameTypeId());
		afterDelete = gameTypeDao.listAllGameTypes();
		//Assert
		Assert.assertEquals(priorToDelete.size() - 1, afterDelete.size());
	}
}
