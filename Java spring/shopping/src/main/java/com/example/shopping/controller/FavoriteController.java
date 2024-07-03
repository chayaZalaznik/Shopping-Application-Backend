package com.example.shopping.controller;

import com.example.shopping.model.Favorite;
import com.example.shopping.model.Item;
import com.example.shopping.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUserId(@PathVariable Long userId) {
        return favoriteService.getFavoritesByUserId(userId);
    }

    @PostMapping("/user/{userId}/add")
    public Favorite addFavorite(@PathVariable Long userId, @RequestBody Item item) {
        return favoriteService.addFavorite(userId, item);
    }

    @DeleteMapping("/user/{userId}/remove/{itemId}")
    public void removeFavorite(@PathVariable Long userId, @PathVariable Long itemId) {
        favoriteService.removeFavorite(userId, itemId);
    }
}
