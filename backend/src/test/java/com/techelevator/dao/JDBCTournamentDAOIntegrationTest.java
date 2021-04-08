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
	private String date = "2021-01-01";
	private String time = "12:00";
	private String image = "image";
	
	
	@Before
	public void setup() {
		
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		tournamentDao = new JDBCTournamentDAO(jdbcTemplate);
		
		tournamentOne = new Tournament();
		tournamentTwo = new Tournament();
		tournamentThree = new Tournament();
		
		tournamentOne.setTournamentId(100);
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
		tournamentOne.setStartTime(time);
		tournamentOne.setTournamentImage(image);
		tournamentOne.setHostId(1);
		
		tournamentTwo.setTournamentId(200);
		tournamentTwo.setGameTypeId(1);
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
		tournamentTwo.setStartTime(time);
		tournamentTwo.setTournamentImage(image);
		tournamentTwo.setHostId(1);
		
		tournamentThree.setTournamentId(300);
		tournamentThree.setGameTypeId(1);
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
		tournamentThree.setStartTime(time);
		tournamentThree.setTournamentImage(image);
		tournamentThree.setHostId(1);
		
	}
	
//	listAllTournaments
//	getTournamentById
//	createTournament
//	deleteTournament
	
	@Test
	public void  createTournament_successfully_creates_a_tournament() {
		//Arrange
		List<Tournament> allTournamentsPrior = new ArrayList<>();
		List<Tournament> allTournamentsAfter = new ArrayList<>();
		
		allTournamentsPrior = tournamentDao.listAllTournaments();
		
		//Act
		Tournament resultOne = tournamentDao.createTournament(tournamentOne);
		Tournament resultTwo = tournamentDao.createTournament(tournamentTwo);
		Tournament resultThree = tournamentDao.createTournament(tournamentThree);
		
		allTournamentsAfter = tournamentDao.listAllTournaments();
		
		//Assert
		Assert.assertEquals(allTournamentsPrior.size() + 3, allTournamentsAfter.size());
		
	}
	
	@Test
	public void  listAllTournaments_successfully_retrieves_all_tournaments() {
		//Arrange
		List<Tournament> beforeCreate = new ArrayList<>();
		
		beforeCreate = tournamentDao.listAllTournaments();
		
		Tournament resultOne = tournamentDao.createTournament(tournamentOne);
		Tournament resultTwo = tournamentDao.createTournament(tournamentTwo);
		Tournament resultThree = tournamentDao.createTournament(tournamentThree);
		
		//Act
		List<Tournament> test = new ArrayList<>();
		test = tournamentDao.listAllTournaments();		
		//Assert
		Assert.assertEquals(beforeCreate.size() + 3, test.size());
		
	}
	
	@Test
	public void  getTournamentById_retrieves_the_correct_tournament() {
		//Arrange
		Tournament resultOne = tournamentDao.createTournament(tournamentOne);
		Tournament test = new Tournament();
		
		//Act
		test = tournamentDao.getTournamentById(resultOne.getTournamentId());
		String name = test.getName();
		
		//Assert
		Assert.assertEquals(resultOne.getName(), name);
		
	}
	
	@Test
	public void  deleteTournament_deletes_the_correct_tournament() {
		//Arrange
		
		Tournament resultOne = tournamentDao.createTournament(tournamentOne);
		List<Tournament> afterCreate = new ArrayList<>();
		List<Tournament> afterDelete = new ArrayList<>();
		//Act
		afterCreate = tournamentDao.listAllTournaments();
		tournamentDao.deleteTournament(resultOne.getTournamentId());
		afterDelete = tournamentDao.listAllTournaments();
		
		//Assert
		Assert.assertEquals(afterCreate.size() - 1, afterDelete.size());
		
	}
}