package com.example.cst438project2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM User u WHERE u.username like %:username%",
            countQuery = "Select count(*) from User",
            nativeQuery = true)
    List<User> findUserByUsername(
            @Param("username") String username
    );
}
