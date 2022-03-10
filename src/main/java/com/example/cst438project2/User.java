package com.example.cst438project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String username;
    private String password;

    @OneToMany
    private List<Wishlist> Wishlist;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        Wishlist = new ArrayList<Wishlist>();
    }

    // IDE really wanted this? A no-arg constructor
    public User(){
        this.username = "";
        this.password = "";
        Wishlist = new ArrayList<Wishlist>();
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
