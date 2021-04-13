//package com.techelevator.dao;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import com.techelevator.model.Matches;
//
//import org.junit.Assert;
//
//public class JDBCMatchesDAOIntegrationTest extends DAOIntegrationTest {
//
//	private MatchesDAO matchesDao;
//	private JdbcTemplate jdbcTemplate;
//	private Matches matchesOne;
//	private Matches matchesTwo;
//	private Matches matchesThree;
//
//	@Before
//	public void setup() {
//		jdbcTemplate = new JdbcTemplate(dataSource);
//		matchesDao = new JDBCMatchesDAO(jdbcTemplate);
//
//		matchesOne = new Matches();
//		matchesTwo = new Matches();
//		matchesThree = new Matches();
//
//		matchesOne.setMatchId(1000);
//		matchesOne.setTournamentId(1);
//		matchesOne.setTeamOneId(1);
//		matchesOne.setTeamTwoId(2);
//		matchesOne.setWinningTeamId(1);
//		matchesOne.setLosingTeamId(2);
//		matchesOne.setWinningTeamScore(5);
//		matchesOne.setLosingTeamScore(4);
//		matchesOne.setMatchDate("2021-01-01");
//		matchesOne.setMatchTime("12:00");
//
//		matchesTwo.setMatchId(2000);
//		matchesTwo.setTournamentId(2);
//		matchesTwo.setTeamOneId(2);
//		matchesTwo.setTeamTwoId(3);
//		matchesTwo.setWinningTeamId(2);
//		matchesTwo.setLosingTeamId(3);
//		matchesTwo.setWinningTeamScore(5);
//		matchesTwo.setLosingTeamScore(4);
//		matchesTwo.setMatchDate("2021-01-01");
//		matchesTwo.setMatchTime("12:00");
//
//		matchesThree.setMatchId(3000);
//		matchesThree.setTournamentId(3);
//		matchesThree.setTeamOneId(3);
//		matchesThree.setTeamTwoId(4);
//		matchesThree.setWinningTeamId(3);
//		matchesThree.setLosingTeamId(4);
//		matchesThree.setWinningTeamScore(5);
//		matchesThree.setLosingTeamScore(4);
//		matchesThree.setMatchDate("2021-01-01");
//		matchesThree.setMatchTime("12:00");
//	}
//	//getAllMatches
//	//getMatchById
//	//createMatch
//	//updateMatch
//	//deleteMatch
//
//	@Test
//	public void getAllMatches_returns_all_matches() {
//		//Arrange
//		List<Matches> matchesBefore = matchesDao.getAllMatches();
//		Matches one = matchesDao.createMatch(matchesOne);
//		Matches two = matchesDao.createMatch(matchesTwo);
//		Matches three = matchesDao.createMatch(matchesThree);
//
//		//Act
//		List<Matches> matchesAfter = matchesDao.getAllMatches();
//
//		//Assert
//		Assert.assertEquals(matchesBefore.size() + 3, matchesAfter.size());
//	}
//
//	@Test
//	public void getMatchById_returns_the_correct_match() {
//		//Arrange
//		Matches one = matchesDao.createMatch(matchesOne);
//		Matches two = matchesDao.createMatch(matchesTwo);
//		Matches three = matchesDao.createMatch(matchesThree);
//
//		//Act
//		Matches resultOne = matchesDao.getMatchById(matchesOne.getMatchId());
//		Matches resultTwo = matchesDao.getMatchById(matchesTwo.getMatchId());
//		Matches resultThree = matchesDao.getMatchById(matchesThree.getMatchId());
//
//		//Assert
//		Assert.assertEquals(one.getTournamentId(), resultOne.getTournamentId());
//		Assert.assertEquals(two.getTeamOneId(), resultTwo.getTeamOneId());
//		Assert.assertEquals(three.getTeamTwoId(), resultThree.getTeamTwoId());
//	}
//
//	@Test
//	public void createMatch_successfully_creates_a_match() {
//		//Arrange
//
//
//		//Act
//		Matches one = matchesDao.createMatch(matchesOne);
//		Matches two = matchesDao.createMatch(matchesTwo);
//		Matches three = matchesDao.createMatch(matchesThree);
//
//		//Assert
//		Assert.assertEquals(matchesOne.getTournamentId(), one.getTournamentId());
//		Assert.assertEquals(matchesTwo.getTeamTwoId(), two.getTeamTwoId());
//		Assert.assertEquals(matchesThree.getTeamOneId(), three.getTeamOneId());
//	}
//
//	@Test
//	public void updateMatch_successfully_updates_a_match() {
//		//Arrange
//		int newOneWinningScore = 10;
//		int newTwoWinningScore = 15;
//		int newThreeWinningScore = 20;
//		matchesOne.setWinningTeamScore(newOneWinningScore);
//		matchesTwo.setWinningTeamScore(newTwoWinningScore);
//		matchesThree.setWinningTeamScore(newThreeWinningScore);
//		Matches one = matchesDao.createMatch(matchesOne);
//		Matches two = matchesDao.createMatch(matchesTwo);
//		Matches three = matchesDao.createMatch(matchesThree);
//
//		//Act
//		matchesDao.updateMatch(matchesOne, 1000);
//		matchesDao.updateMatch(matchesTwo, 2000);
//		matchesDao.updateMatch(matchesThree, 3000);
//		Matches resultOne = matchesDao.getMatchById(one.getMatchId());
//		Matches resultTwo = matchesDao.getMatchById(two.getMatchId());
//		Matches resultThree = matchesDao.getMatchById(three.getMatchId());
//
//		//Assert
//		Assert.assertEquals(newOneWinningScore, resultOne.getWinningTeamScore());
//		Assert.assertEquals(newTwoWinningScore, resultTwo.getWinningTeamScore());
//		Assert.assertEquals(newThreeWinningScore, resultThree.getWinningTeamScore());
//	}
//
//	@Test
//	public void deleteMatch_deletes_a_match() {
//		//Arrange
//		List<Matches> matchesBefore = matchesDao.getAllMatches();
//		Matches one = matchesDao.createMatch(matchesOne);
//		Matches two = matchesDao.createMatch(matchesTwo);
//		Matches three = matchesDao.createMatch(matchesThree);
//
//		//Act
//		matchesDao.deleteMatch(one.getMatchId());
//		matchesDao.deleteMatch(two.getMatchId());
//		List<Matches> matchesAfter = matchesDao.getAllMatches();
//
//		//Assert
//		Assert.assertEquals(matchesBefore.size() + 1, matchesAfter.size());
//
//	}
//}
