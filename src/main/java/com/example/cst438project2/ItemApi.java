package com.example.cst438project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * A Rest Controller for functions related to the Item class
 */

@RestController
@RequestMapping(path="/api")
public class ItemApi {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    /**
     * Gathers all items in the databse and sends them back.
     * @return all items in itemRepository
     */
    @GetMapping(path="/allItems")
    public Iterable<Item> getAllItems(){
        return itemRepository.findAll();
    }

    /**
     * Searches through itemRepository for a specific item based on its id and returns it.
     * @param id the integer id number randomly generated for each item upon being stored in the database
     * @return the Item with a matching id, if it exists.
     */
    @GetMapping(path="/item")
    public Optional<Item> findItemById(@RequestParam Integer id){
            return itemRepository.findById(id);
    }

    /**
     * DEBUGGING adds a single item to the database
     * @param itemName the string name of an item given by a user
     * @param itemUrl the string url link to an external website given by the user
     * @param priority the string number that indicates how important an item is
     * @param description the string description given by the user for the item created
     * @return string indicating a successful save
     */
    @PostMapping(path="/addItem")
    public String addItem(@RequestParam String itemName, @RequestParam String itemUrl,
                          @RequestParam int priority, @RequestParam String description){
        Item item = new Item(itemName, itemUrl, priority, description);
        itemRepository.save(item);
        return "saved";
    }

    /**
     * Creates a new Item and saves it to the repository as well as to a specific wishlist.
     * @param listId the integer id randomly generated for a list added to the wishlist database
     * @param itemName the string name of an item given by a user
     * @param itemUrl the string url link to an external website given by the user
     * @param priority the string number that indicates how important an item is
     * @param description the string description given by the user for the item created
     * @return string indicating a successful save or an error
     */
    @PutMapping(path="/addToList")
    public String addItemToList(@RequestParam int listId, @RequestParam String itemName, @RequestParam String itemUrl,
                                @RequestParam int priority, @RequestParam String description){
        Item item = new Item(itemName, itemUrl, priority, description);
        itemRepository.save(item);
        Wishlist wishList = new Wishlist();
        // retrieve wishlist by id, check to make sure list exists first
        if(wishlistRepository.findById(listId).isEmpty()) {
            return "wishlist does not exist";
        }else{
            wishList = wishlistRepository.findById(listId).get();
            // add item to wishlist
            wishList.getItems().add(item);
            wishlistRepository.save(wishList);
            return "saved";
        }

    }

    /**
     * Retrieves all the items of a specific list, indicated by the id number.
     * @param listId the integer id randomly generated for each wishlist when they're stored.
     * @return the items of the matching list, if they are there.
     */
    @GetMapping(path="/listItems")
    public List<Item> getListItems(@RequestParam int listId){
        Wishlist list = wishlistRepository.findById(listId).get();
        return list.getItems();
    }

    /**
     * Seeks an existing Item from the database and updates any of its parameters with new ones provided
     * @param itemId the integer id number randomly generated for each item upon being stored in the database
     * @param itemName the string of a new name of an item given by a user
     * @param itemUrl the string of a new url link to an external website given by the user
     * @param priority the string of a new number that indicates how important an item is
     * @param description the string of a new description given by the user for the item created
     * @return string indicating a success or an error
     */
    @PatchMapping(path="/updateItem")
    public String editItem(@RequestParam int itemId, @RequestParam(required = false) String itemName,
                           @RequestParam(required = false) String itemUrl,
                           @RequestParam(required = false) int priority,
                           @RequestParam(required = false) String description){

        if(itemName == null && itemUrl == null && description == null){ //priority == null
            return "no parameters for change given";
        }

        if(itemRepository.existsById(itemId)){
            Item item = itemRepository.findById(itemId).get();
            if(itemName != null){
                item.setItemName(itemName);
            }
            if(itemUrl != null){
                item.setItemUrl(itemUrl);
            }
//            if(priority != null){
                item.setPriority(priority);
//            }
            if(description != null){
                item.setDescription(description);
            }
            itemRepository.save(item);
            return "saved";
        }

        return "update error";
    }

    /**
     * Deletes an item from the database using its id
     * @param itemId the integer id number randomly generated for each item upon being stored in the database
     * @return simple confirmation string
     */
    @DeleteMapping(path="/deleteItem")
    public String deleteItem(@RequestParam Integer itemId){
        itemRepository.deleteById(itemId);
        return "Item Deleted";
    }


}
