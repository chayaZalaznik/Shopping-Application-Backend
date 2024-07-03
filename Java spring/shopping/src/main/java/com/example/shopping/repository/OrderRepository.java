// OrderRepository.java
package com.example.shopping.repository;

import com.example.shopping.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    @Query("SELECT o FROM Order o WHERE o.userId = :userId AND o.status = 'OPEN'")
    Order findOpenOrderByUserId(@Param("userId") Long userId);

    List<Order> findByUserIdAndStatus(Long userId, String status); // Add this line

	Optional<Order> findByIdAndUserId(Long orderId, Long userId);
    void deleteByUserId(Long userId);

}