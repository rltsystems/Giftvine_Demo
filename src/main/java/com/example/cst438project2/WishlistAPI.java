package com.example.cst438project2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class WishlistAPI {

    @Autowired
    WishlistRepository wishlistRepository;
    UserRepository userRepository;

    @GetMapping(path="/allLists")
    public Iterable<Wishlist> getAllLists(){
        return wishlistRepository.findAll();
    }

    @PutMapping(path="/addList")
    public String addList(@RequestParam String username, @RequestParam String listName) {
        Wishlist wishList = new Wishlist(listName);
        wishlistRepository.save(wishList);

        // find the user by their name, add wishlist to their list
        User user = userRepository.findUserByUsername(username).get(0);
        user.getWishlist().add(wishList);
        return "saved";
    }




}
