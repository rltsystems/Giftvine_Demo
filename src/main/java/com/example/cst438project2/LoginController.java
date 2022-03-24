package com.example.cst438project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(@RequestParam String username, @RequestParam String password){
        userRepository.findUserByUsername(username);
        return username;
    }
}
