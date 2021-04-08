package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role);
    
    void updateUser(User user);
    
//	Does not work because it violates multiple foreign key constraints
//	may set up a new role (DEAVTIVATED) that removes all abilities instead of deleting the user.
//    void deleteUser(int userId);
}
