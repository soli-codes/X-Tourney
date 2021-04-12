package com.techelevator.dao;

import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.TeamName;
import com.techelevator.model.Tournament;

public class JDBCTeamNameDAOIntegrationTest extends DAOIntegrationTest{
	
	private TeamNameDAO teamNameDao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		teamNameDao = new JDBCTeamNameDAO(jdbcTemplate);		
	}
	
	@Test
	public void createNewTeam_ListAllTeamName_properly_creates_team_lists_all_teams() {
		//Arrange
		List<TeamName> prior = new ArrayList<>();
		List<TeamName> postCreate = new ArrayList<>();
		TeamName test = new TeamName();
		test.setTeamName("TEST");
		//Act
		prior = teamNameDao.listAllTeamNames();
		teamNameDao.createNewTeam(test);
		postCreate = teamNameDao.listAllTeamNames();
		//Assert
		Assert.assertEquals(prior.size() + 1, postCreate.size());
	}
	
	@Test
	public void getTeamNameById_properly_gets_team_() {
		//Arrange
		TeamName test = new TeamName();
		TeamName test1 = new TeamName();
		test.setTeamName("TEST");
		//Act
		test = teamNameDao.createNewTeam(test);
		String nameTest = test.getTeamName();
		int idTest = test.getTeamId();
		test1 = teamNameDao.getTeamNameById(idTest);
		//Assert
		Assert.assertEquals(idTest, test1.getTeamId());
		Assert.assertEquals("TEST", test1.getTeamName());
	}
	
	@Test
	public void updateTeam_properly_changes_values() {
		//Arrange
		TeamName test = new TeamName();
		test.setTeamName("Initial");
		test = teamNameDao.createNewTeam(test);
		test.setTeamName("TEST");
		TeamName result = new TeamName();
		//Act
		teamNameDao.updateTeam(test, test.getTeamId());
		result = teamNameDao.getTeamNameById(test.getTeamId());
		String name = result.getTeamName();
		//Assert
		Assert.assertEquals("TEST", name);
	}
	
	@Test
	public void deleteTeam_properly_deletes_team() {
		//Arrange
		List<TeamName> priorDelete = new ArrayList<>();
		List<TeamName> postDelete = new ArrayList<>();
		TeamName test = new TeamName();
		test.setTeamName("TEST");
		TeamName test1 = new TeamName();
		//Act
		test1 = teamNameDao.createNewTeam(test);
		priorDelete = teamNameDao.listAllTeamNames();
		teamNameDao.deleteTeam(test1.getTeamId());
		postDelete = teamNameDao.listAllTeamNames();
		//Assert
		Assert.assertEquals(priorDelete.size() - 1, postDelete.size());
	}
	
}
