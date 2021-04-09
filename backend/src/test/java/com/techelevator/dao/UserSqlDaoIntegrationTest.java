package com.techelevator.dao;

import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import javax.sql.DataSource;

public class UserSqlDaoIntegrationTest extends DAOIntegrationTest {

    private UserSqlDAO userSqlDAO;
    private User userOne;
    private User userTwo;
    private User userThree;
    private int wins = 5;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        userSqlDAO = new UserSqlDAO(jdbcTemplate);
        
        userOne = new User();
        userTwo = new User();
        userThree = new User();
        
        //userOne.setId(100L);
        userOne.setUsername("UserOne");
        userOne.setWins(wins);
        userOne.setLosses(1);
        userOne.setTournamentWins(1);
        userOne.setTournamentsEntered(1);
        userOne.setUserImage("image");
        
        //userTwo.setId(200L);
        userTwo.setUsername("UserOne");
        userTwo.setWins(wins);
        userTwo.setLosses(2);
        userTwo.setTournamentWins(2);
        userTwo.setTournamentsEntered(2);
        userTwo.setUserImage("image");
        
        //userThree.setId(300L);
        userThree.setUsername("UserOne");
        userThree.setWins(wins);
        userThree.setLosses(3);
        userThree.setTournamentWins(3);
        userThree.setTournamentsEntered(3);
        userThree.setUserImage("image");
    }

    @Test
    public void createNewUser() {
        boolean userCreated = userSqlDAO.create("TEST_USER","test_password","user");
        Assert.assertTrue(userCreated);
        User user = userSqlDAO.findByUsername("TEST_USER");
        Assert.assertEquals("TEST_USER", user.getUsername());
    }
    
    //getUserById
    //findAll
    //updateUser
    
    @Test
    public void getUserById_retrieves_the_correct_user() {
    	//Arrange
    	boolean oneCreated = userSqlDAO.create(userOne.getUsername(), "test_password","user");
    	long oneId = userSqlDAO.findIdByUsername(userOne.getUsername());
    	
    	//Act
    	User resultOne = userSqlDAO.getUserById(oneId);
    	
    	//Assert
    	Assert.assertEquals(userOne.getUsername(), resultOne.getUsername());

    }
    
    
    @Test
    public void findAll_retrieves_all_users() {
    	//Arrange
    	List<User> allUsersBefore = userSqlDAO.findAll();
    	
       	boolean oneCreated = userSqlDAO.create(userOne.getUsername(), "test_password","user");
    	boolean twoCreated = userSqlDAO.create(userTwo.getUsername(), "test_password","user");
    	boolean threeCreated = userSqlDAO.create(userThree.getUsername(), "test_password","user");
    	
    	//Act
    	List<User> allUsersAfter = userSqlDAO.findAll();
    	
    	//Assert
    	Assert.assertEquals(allUsersBefore.size() + 3, allUsersAfter.size());
    	
    }
    
    
    @Test
    public void updateUser_updates_the_correct_user() {
    	//Arrange
    	boolean oneCreated = userSqlDAO.create(userOne.getUsername(), "test_password","user");
    	boolean twoCreated = userSqlDAO.create(userTwo.getUsername(), "test_password","user");
    	boolean threeCreated = userSqlDAO.create(userThree.getUsername(), "test_password","user");
    	
    	//Act
    	userSqlDAO.updateUser(userOne);
    	userSqlDAO.updateUser(userTwo);
    	userSqlDAO.updateUser(userThree);
    	
    	//Assert
    	Assert.assertEquals(wins, userOne.getWins());
    	Assert.assertEquals(wins, userTwo.getWins());
    	Assert.assertEquals(wins, userThree.getWins());
    }

}
