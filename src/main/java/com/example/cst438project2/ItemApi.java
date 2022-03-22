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
    private WishlistRepository wishlistRepository;

    //displays all items
    @GetMapping(path="/allItems") //Just a test call, I don't think we need it for final
    public Iterable<Item> getAllItems(){
        return itemRepository.findAll();
    }

    //when return type is Optional, you can use isPresent() to make sure it exists
    @GetMapping(path="/item")
    public Optional<Item> findItemById(@RequestParam Integer id){
            return itemRepository.findById(id);
    }

    // for debugging, adds only one item to repository
    @PostMapping(path="/addItem")
    public String addItem(@RequestParam String itemName, @RequestParam String itemUrl,
                          @RequestParam String priority, @RequestParam String description){
        Item item = new Item(itemName, itemUrl, priority, description);
        itemRepository.save(item);
        return "saved";
    }


    @PatchMapping(path="/updateItem")
    public String editItem(@RequestParam int itemId, @RequestParam(required = false) String itemName,
                           @RequestParam(required = false) String itemURL,
                           @RequestParam(required = false) String itemPriority,
                           @RequestParam(required = false) String itemDescription){

        if(itemName == null && itemURL == null && itemPriority == null && itemDescription == null){
            return "no parameters for change given";
        }

        if(itemRepository.existsById(itemId)){
            Item item = itemRepository.findById(itemId).get();
            if(itemName != null){
                item.setItemName(itemName);
            }
            if(itemURL != null){
                item.setItemUrl(itemURL);
            }
            if(itemPriority != null){
                item.setPriority(itemPriority);
            }
            if(itemDescription != null){
                item.setDescription(itemDescription);
            }
            itemRepository.save(item);
            return "saved";
        }

        return "update error";
    }

    @DeleteMapping(path="/deleteItem")
    public @ResponseBody String deleteUser(@RequestParam Integer itemId){
        itemRepository.deleteById(itemId);
        return "Deleted";
    }

    @PutMapping(path="/addToList")
    public String addItemToList(@RequestParam int listId, @RequestParam String itemName, @RequestParam String itemUrl,
                                @RequestParam String priority, @RequestParam String description){
        Item item = new Item(itemName, itemUrl, priority, description);
        itemRepository.save(item);

        // retrieve wishlist by id
        Wishlist wishList = wishlistRepository.findById(listId).get();

        // add item to wishlist
        wishList.getItems().add(item);

        return "saved";
    }
}
