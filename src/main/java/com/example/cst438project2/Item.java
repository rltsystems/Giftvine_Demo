package com.example.cst438project2;


import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * A class to represent an item created by a user for a wishlist. It is stored in the ItemRespository.
 */

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;

    private String itemName;
    private String itemUrl;
    private int priority;
    private String description;

    public Item(String itemName, String itemUrl, int priority, String description){
        this.itemName = itemName;
        this.description = description;
        this.priority = priority;
        this.itemUrl = itemUrl;
    }

    public Item(){
        this.itemName = "";
        this.description = "";
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId) && Objects.equals(itemName, item.itemName) && Objects.equals(itemUrl, item.itemUrl) && Objects.equals(priority, item.priority) && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, itemUrl, priority, description);
    }
}
