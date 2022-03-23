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

    @GetMapping(path="/allUsers")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping(path="/addUser")
    public String addUser(@RequestParam String username, @RequestParam String password,
                          @RequestParam (required = false) Boolean isAdmin) {
        if(!userRepository.findDistinctByUsername(username).isEmpty()){
            return "User Already Exists";
        }
        User user = new User(username, password);
        if(isAdmin != null){
            user.setAdmin(isAdmin);
        }
        userRepository.save(user);
        return "saved";
    }

    @GetMapping(path="/findUserByUsername")
    public List<User> findUserByUsername(@RequestParam (defaultValue = "test") String username){
        return userRepository.findUserByUsername(username);
    }

    @DeleteMapping(path="/deleteUser")
    public String deleteUser(@RequestParam String username){
        User user = new User(username, "");
        user = findUserByUsername(username).get(0); //maybe add an ispresent check

        userRepository.delete(user);
        return "Deleted";
    }

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