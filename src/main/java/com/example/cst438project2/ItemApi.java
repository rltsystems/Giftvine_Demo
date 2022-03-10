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

    //displays all items
    @GetMapping(path="/allItems") //Just a test call, i dont think we need it for final
    public Iterable<Item> getAllItems(){
        return itemRepository.findAll();
    }

    //when return type is Optional, you can use isPresent() to make sure it exists
    @GetMapping(path="/items")
    public Optional<Item> findItemById(@RequestParam Integer id){
        return itemRepository.findById(id);
    }

    //adds only one item rn
    @PostMapping(path="/addItems")
    public String addItem(@RequestParam String itemName, @RequestParam String description){
        Item item = new Item(itemName, description);
        itemRepository.save(item);
        return "saved";
    }

}
