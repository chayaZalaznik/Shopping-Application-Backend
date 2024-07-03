package com.example.shopping.service;

import com.example.shopping.model.Item;
import com.example.shopping.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }
    public List<Item> searchItems(String query) {
        return itemRepository.findByTitleContainingIgnoreCase(query);
    }
}
