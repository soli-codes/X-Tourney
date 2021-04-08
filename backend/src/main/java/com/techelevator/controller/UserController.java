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

import com.techelevator.dao.UserDAO;
import com.techelevator.model.User;

@RestController
@CrossOrigin
@RequestMapping("")
public class UserController {
	
	private UserDAO dao;
	
	public UserController(UserDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping( path = "/users", method = RequestMethod.GET)
	public List<User> list() {
		return dao.findAll();
	}

	@RequestMapping( path = "/users/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable long id) {
		return dao.getUserById(id);
	}
	
	@RequestMapping( path = "/users/update", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		dao.updateUser(user);
	}
	
//	Does not work because it violates multiple foreign key constraints
//	may set up a new role (DEAVTIVATED) that removes all abilities instead of deleting the user.
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	@RequestMapping( path = "/users/{id}", method = RequestMethod.DELETE)
//	public void deleteUser(@PathVariable int id) {
//		dao.deleteUser(id);
//	}
}
