package com.example.shopping.service;

import com.example.shopping.model.Favorite;
import com.example.shopping.model.Item;
import com.example.shopping.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public List<Favorite> getFavoritesByUserId(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public Favorite addFavorite(Long userId, Item item) {
        Favorite existingFavorite = favoriteRepository.findByUserIdAndItemId(userId, item.getId());
        if (existingFavorite != null) {
            return existingFavorite;
        }

        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setItem(item);
        return favoriteRepository.save(favorite);
    }

    public void removeFavorite(Long userId, Long itemId) {
        Favorite favorite = favoriteRepository.findByUserIdAndItemId(userId, itemId);
        if (favorite != null) {
            favoriteRepository.delete(favorite);
        }
    }
}
