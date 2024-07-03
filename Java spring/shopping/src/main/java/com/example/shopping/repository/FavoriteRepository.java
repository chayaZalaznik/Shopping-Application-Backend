package com.example.shopping.repository;

import com.example.shopping.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);
    Favorite findByUserIdAndItemId(Long userId, Long itemId);
    void deleteByUserId(Long userId);

}
