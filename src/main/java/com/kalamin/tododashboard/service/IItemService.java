package com.kalamin.tododashboard.service;

import com.kalamin.tododashboard.model.Item;
import com.kalamin.tododashboard.model.User;

import java.util.List;

public interface IItemService {
    void saveItem(Item item);
    void deleteItem(long id);
    void updateItem(Item item);
    List<Item> getAll(User user);
}
