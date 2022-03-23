package com.example.cst438project2;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    public String addUser(@RequestParam String username, @RequestParam String password,
                          @RequestParam (required = false) Boolean isAdmin) {
        User user = new User(username, password);
        if(isAdmin != null){
            user.setAdmin(isAdmin);
        }
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
        user = findUserByUsername(username).get(0); //maybe add an ispresent check

        userRepository.delete(user);
        return "Deleted";
    }

    /**
     * Logs User into their account
     * @param username the string username chosen by the user
     * @param password the string password that authenticates the user
     * @return a success or error message
     */
    @PostMapping(path="/login")
    public String login(@RequestParam String username, @RequestParam String password){

        if(userRepository.findUserByUsername(username).isEmpty()){
            return "userNull";
        }
        else{
            User user = userRepository.findUserByUsername(username).get(0);
            if(!Objects.equals(user.getPassword(), password)){ // same as user.getPass != password
                return "wrongPass";
            }
            else if(user.isAdmin()){
                return "adminLogin";
            }
            else{
                return "loginSuccess";
            }
        }
    }

    /**
     * updates User information
     * @param originalUsername the string of the initial username for searching the database
     * @param newUsername the string new username to change out
     * @param password the string password  used to authenticate
     * @param isAdmin boolean indicator of admin status
     * @return a success message
     */
    @PatchMapping(path="/updateUser")
    public String updateUser(@RequestParam String originalUsername,
                             @RequestParam (required = false) String newUsername,
                             @RequestParam (required = false) String password,
                             @RequestParam (required = false) Boolean isAdmin){
        User user = userRepository.findUserByUsername(originalUsername).get(0);
        if(newUsername != null){
            user.setUsername(newUsername);
        }
        if(password != null){
            user.setPassword(password);
        }
        if(isAdmin != null){
            user.setAdmin(isAdmin);
        }
        userRepository.save(user);
        return originalUsername + " has been updated";
    }
}