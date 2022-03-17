package com.example.cst438project2;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository for the local database of Item objects
 */

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
