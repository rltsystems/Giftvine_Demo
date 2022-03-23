package com.example.cst438project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest API for functionality relating to the User object such as logging in and creating or removing accounts
 */

@RestController
@RequestMapping(path="/api")
public class UserApi {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all currently stored users
     * @return all User objects in userRepository
     */
    @GetMapping(path="/allUsers")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Creates a new user using a username and password, and stores it in the repository
     * @param username the string chosen as the front end identifier for the user
     * @param password the string password for the user account
     * @return
     */
    @PostMapping(path="/addUser")
    public String addUser(@RequestParam String username, @RequestParam String password) {
        User user = new User(username, password);
        userRepository.save(user);
        return "saved";
    }

    /**
     * Searches repository for an existing user with the indicated username
     * @param username the string used to search the database for a User
     * @return the matching User object, if it exists
     */
    @GetMapping(path="/findUserByUsername")
    public List<User> findUserByUsername(@RequestParam (defaultValue = "test") String username){
        return userRepository.findUserByUsername(username);
    }

    /**
     * Deletes a user from the repository using their username
     * @param username the string used to search the database for a User
     * @return a success message
     */
    @DeleteMapping(path="/deleteUser")
    public String deleteUser(@RequestParam String username){
        User user = new User(username, "");
        user = findUserByUsername(username).get(0);

        userRepository.delete(user);
        return "Deleted";
    }

}