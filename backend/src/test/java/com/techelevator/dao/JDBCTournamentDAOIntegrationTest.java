package com.techelevator.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Tournament;

public class JDBCTournamentDAOIntegrationTest extends DAOIntegrationTest {
	
	private TournamentDAO tournamentDao;
	private JdbcTemplate jdbcTemplate;
	private Tournament tournamentOne;
	private Tournament tournamentTwo;
	private Tournament tournamentThree;
	private Date date = new Date (01/01/2021);
	
	
	@Before
	public void setup() {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		tournamentDao = new JDBCTournamentDAO(jdbcTemplate);
		
		tournamentOne = new Tournament();
		tournamentTwo = new Tournament();
		tournamentThree = new Tournament();
		
		tournamentOne.setTournamentId(1);
		tournamentOne.setGameTypeId(1);
		tournamentOne.setName("One");
		tournamentOne.setDescription("OneOne");
		tournamentOne.setPrivate(true);
		tournamentOne.setOpen(true);
		tournamentOne.setMaxTeamCount(8);
		tournamentOne.setEliminationType("Single");
		tournamentOne.setStartDate(date);
		tournamentOne.setEndDate(date);
		tournamentOne.setSignUpOpen(date);
		tournamentOne.setSignUpClose(date);
		
		tournamentTwo.setTournamentId(2);
		tournamentTwo.setGameTypeId(2);
		tournamentTwo.setName("Two");
		tournamentTwo.setDescription("TwoTwo");
		tournamentTwo.setPrivate(true);
		tournamentTwo.setOpen(true);
		tournamentTwo.setMaxTeamCount(10);
		tournamentTwo.setEliminationType("Double");
		tournamentTwo.setStartDate(date);
		tournamentTwo.setEndDate(date);
		tournamentTwo.setSignUpOpen(date);
		tournamentTwo.setSignUpClose(date);
		
		tournamentThree.setTournamentId(3);
		tournamentThree.setGameTypeId(3);
		tournamentThree.setName("Three");
		tournamentThree.setDescription("ThreeThree");
		tournamentThree.setPrivate(true);
		tournamentThree.setOpen(true);
		tournamentThree.setMaxTeamCount(6);
		tournamentThree.setEliminationType("Single");
		tournamentThree.setStartDate(date);
		tournamentThree.setEndDate(date);
		tournamentThree.setSignUpOpen(date);
		tournamentThree.setSignUpClose(date);
		
	}
	
//	listAllTournaments
//	getTournamentById
//	createTournament
//	deleteTournament
	
	@Test
	public void  createTournament_successfully_creates_a_tournament() {
		//Arrange
		List<Tournament> allTournaments = new ArrayList<>();
		
		//Act
		Tournament resultOne = tournamentDao.createTournament(tournamentOne);
		Tournament resultTwo = tournamentDao.createTournament(tournamentTwo);
		Tournament resultThree = tournamentDao.createTournament(tournamentThree);
		
		allTournaments =tournamentDao.listAllTournaments();
		
		//Assert
		Assert.assertEquals(3, allTournaments.size());
		Assert.assertEquals(tournamentOne.getTournamentId(), resultOne.getTournamentId());
		
	}
	
	@Test
	public void  listAllTournaments_successfully_retrieves_all_tournaments() {
		//Arrange
		
		
		//Act
		
		
		//Assert
		
		
	}
	
	@Test
	public void  getTournamentById_retrieves_the_correct_tournament() {
		//Arrange
		
		
		//Act
		
		
		//Assert
		
		
	}
	
	@Test
	public void  deleteTournament_deletes_the_correct_tournament() {
		//Arrange
		
		
		//Act
		
		
		//Assert
		
		
	}
}