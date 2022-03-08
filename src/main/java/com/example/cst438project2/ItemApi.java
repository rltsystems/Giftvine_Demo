package com.example.cst438project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/api")
public class ItemApi {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(path="/allItems") //Just a test call, i dont think we need it for final
    public @ResponseBody Iterable<Item> getAllItems(){
        return itemRepository.findAll();
    }

    @GetMapping(path="/items")
    public @ResponseBody
    Optional<Item> findItemById(@RequestParam Integer id){
        return itemRepository.findById(id);
    }

    @PostMapping(path="/addItems")
    public @ResponseBody String addItem(@RequestParam String itemName, @RequestParam String description){
        Item item = new Item();
        item.setItemName(itemName);
        item.setDescription(description);

        itemRepository.save(item);

        return "saved";
    }

}
