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

    /**
     * Retrieves all wishlists from the repository
     * @return all WishLists currently stored
     */
    @GetMapping(path="/allLists")
    public Iterable<Wishlist> getAllLists(){
        return wishlistRepository.findAll();
    }

    /**
     * Retrieves all the wishlists of a specified user
     * @param username the string name of the user whos lists will be retrieved
     * @return the wishlists of the matching user, if they exist
     */
    @GetMapping(path="/userLists")
    public Iterable<Wishlist> getUserLists(@RequestParam String username){
        User user = userRepository.findUserByUsername(username).get(0);
        return user.getWishlist();
    }

    /**
     * Retrieves a specific Wishlist using its id number
     * @param listId the integer id randomly generated for each Wishlist when it is stored in the repository
     * @return the wishlist with a matching id number, if it exists
     */
    @GetMapping(path="/list")
    public Optional<Wishlist> getWishlist(@RequestParam int listId){
        return wishlistRepository.findById(listId);
    }

    /**
     * Creates a new Wishlist and adds it to the repository as well as assigning it to the indicated User.
     * @param username the string name of the user that the wishlist will be assigned to
     * @param listName the string name of the list
     * @return a message of a successful saving
     */
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
