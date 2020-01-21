package com.kalamin.tododashboard.repository;

import com.kalamin.tododashboard.model.Item;
import com.kalamin.tododashboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    void deleteItemByIdEquals(long id);

    @Query("SELECT i from Item i where i.user = :user order by i.priority desc")
    List<Item> getAllItems(@Param("user") User user);
}
