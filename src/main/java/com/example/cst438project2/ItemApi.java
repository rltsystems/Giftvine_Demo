package com.example.cst438project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class ItemApi {

    @Autowired
    private ItemRepository itemRepository;
    private WishlistRepository wishlistRepository;

    //displays all items
    @GetMapping(path="/allItems") //Just a test call, I don't think we need it for final
    public Iterable<Item> getAllItems(){
        return itemRepository.findAll();
    }

    //when return type is Optional, you can use isPresent() to make sure it exists
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

    @PostMapping(path="/editItem")
    public String editItem(@RequestParam String itemName, @RequestParam String itemUrl,
                           @RequestParam int priority, @RequestParam String description){
        Item item = new Item(itemName, itemUrl, priority, description);
        itemRepository.save(item);
        return "saved";
    }


    @PutMapping(path="/addToList")
    public String addItemToList(@RequestParam int listId, @RequestParam String itemName, @RequestParam String itemUrl,
                                @RequestParam int priority, @RequestParam String description){
        Item item = new Item(itemName, itemUrl, priority, description);
        itemRepository.save(item);

        // retrieve wishlist by id
        Wishlist wishList = wishlistRepository.findById(listId).get();

        // add item to wishlist
        wishList.getItems().add(item);

        return "saved";
    }
}
