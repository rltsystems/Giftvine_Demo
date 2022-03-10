package com.example.cst438project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String addUser(@RequestParam String username, @RequestParam String password) {
        User user = new User(username, password);
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
        user = findUserByUsername(username).get(0);

        userRepository.delete(user);
        return "Deleted";
    }

}