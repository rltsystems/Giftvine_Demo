package com.example.cst438project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class to represent the User of the wishlist service, including login info and their list of Wishlists
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String username;
    private String password;
    private boolean isAdmin;

    @OneToMany
    private List<Wishlist> Wishlist;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.isAdmin = false;
        Wishlist = new ArrayList<Wishlist>();
    }

    // IDE really wanted this? A no-arg constructor
    public User(){
        this.username = "";
        this.password = "";
        Wishlist = new ArrayList<Wishlist>();
    }

    public List<Wishlist> getWishlist() {
        return Wishlist;
    }

    public void setWishlist(List<Wishlist> wishlist) {
        Wishlist = wishlist;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isAdmin == user.isAdmin && Objects.equals(Id, user.Id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(Wishlist, user.Wishlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, username, password, isAdmin, Wishlist);
    }
}
