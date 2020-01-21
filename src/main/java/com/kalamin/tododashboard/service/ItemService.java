package com.kalamin.tododashboard.service;

import com.kalamin.tododashboard.model.Item;
import com.kalamin.tododashboard.model.User;
import com.kalamin.tododashboard.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ItemService implements IItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void saveItem(Item item) {
        itemRepository.saveAndFlush(item);
    }

    @Override
    public void deleteItem(long item) {
        itemRepository.deleteItemByIdEquals(item);
    }

    @Override
    public void updateItem(Item item) {
        saveItem(item);
    }

    @Override
    public List<Item> getAll(User user) {
       // return itemRepository.findAll(Sort.by(Sort.Direction.DESC, "priority"));
        return itemRepository.getAllItems(user);
    }
}
