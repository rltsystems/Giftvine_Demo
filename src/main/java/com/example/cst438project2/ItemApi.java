package com.example.cst438project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * A rest controller for functions related to the Item class
 */

@RestController
@RequestMapping(path="/api")
public class ItemApi {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    //displays all items
    @GetMapping(path="/allItems") //Just a test call, I don't think we need it for final
    public Iterable<Item> getAllItems(){
        return itemRepository.findAll();
    }

    @GetMapping(path="/items")
    public Optional<Item> findItemById(@RequestParam Integer id){
        return itemRepository.findById(id);
    }

    // for debugging, adds only one item to repository
    @PostMapping(path="/addItem")
    public String addItem(@RequestParam String itemName, @RequestParam String itemUrl,
                          @RequestParam int priority, @RequestParam String description){
        Item item = new Item(itemName, itemUrl, priority, description);
        itemRepository.save(item);
        return "saved";
    }

    // TODO: finish this. determine if default values are a good idea?
    @PostMapping(path="/editItem")
    public String editItem(@RequestParam int itemId, @RequestParam String itemName, @RequestParam String itemUrl,
                           @RequestParam int priority, @RequestParam String description){

        //Item item = new Item(itemName, itemUrl, priority, description);
        //itemRepository.save(item);
        return "saved";
    }

    @DeleteMapping(path="/deleteItem")
    public @ResponseBody String deleteUser(@RequestParam Integer itemId){
        itemRepository.deleteById(itemId);
        return "Deleted";
    }

    @PutMapping(path="/addToList")
    public String addItemToList(@RequestParam int listId, @RequestParam String itemName, @RequestParam String itemUrl,
                                @RequestParam int priority, @RequestParam String description){
        Item item = new Item(itemName, itemUrl, priority, description);
        itemRepository.save(item);
        Wishlist wishList = new Wishlist();
        // retrieve wishlist by id, check to make sure list exists first
        if(wishlistRepository.findById(listId).isPresent()) {
            wishList = wishlistRepository.findById(listId).get();
        }
        // add item to wishlist
        wishList.getItems().add(item);
        wishlistRepository.save(wishList);
        return "saved";
    }
}
