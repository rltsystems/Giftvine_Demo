package com.example.cst438project2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Rest API for functionality relating to the Wishlist object such as adding, removing, and editing objects
 */

@RestController
@RequestMapping(path="/api")
public class WishlistApi {

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path="/allLists")
    public Iterable<Wishlist> getAllLists(){
        return wishlistRepository.findAll();
    }

    @GetMapping(path="/list")
    public Optional<Wishlist> getWishlist(@RequestParam int listId){
        return wishlistRepository.findById(listId);
    }

    @PutMapping(path="/addList")
    public String addList(@RequestParam String username, @RequestParam String listName) {
        Wishlist wishList = new Wishlist(listName);
        wishlistRepository.save(wishList);

        // find the user by their name, add wishlist to their list
        User user = userRepository.findUserByUsername(username).get(0);
        user.getWishlist().add(wishList);
        userRepository.save(user);
        return "saved";
    }
}
