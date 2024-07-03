// OrderController.java
package com.example.shopping.controller;
import com.example.shopping.model.Order;
import com.example.shopping.model.Item;
import com.example.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }
    @GetMapping("/closed/{userId}")
    public List<Order> getClosedOrdersByUserId(@PathVariable Long userId) {
        return orderService.getClosedOrdersByUserId(userId);
    }
    @PostMapping("/user/{userId}")
    public Order createOrder(@PathVariable Long userId) {
        return orderService.createOrder(userId);
    }

    @PutMapping("/{orderId}/user/{userId}")
    public Order updateOrder(@PathVariable Long orderId, @PathVariable Long userId, @RequestBody Order order) {
        return orderService.updateOrder(orderId, userId, order);
    }

    @DeleteMapping("/{orderId}/user/{userId}")
    public void deleteOrder(@PathVariable Long orderId, @PathVariable Long userId) {
        orderService.deleteOrder(orderId, userId);
    }

    @PostMapping("/{orderId}/user/{userId}/addItem")
    public Order addItemToOrder(@PathVariable Long orderId, @PathVariable Long userId, @RequestBody Item item) {
        return orderService.addItemToOrder(orderId, userId, item);
    }

    @DeleteMapping("/{orderId}/user/{userId}/removeItem/{itemId}")
    public Order removeItemFromOrder(@PathVariable Long orderId, @PathVariable Long userId, @PathVariable Long itemId) {
        return orderService.removeItemFromOrder(orderId, userId, itemId);
    }

    @GetMapping("/open/{userId}")
    public Order getOpenOrder(@PathVariable Long userId) {
        return (Order) orderService.getOpenOrderByUserId(userId);
    }
    @PutMapping("/{orderId}/user/{userId}/updateItem/{itemId}")
    public Order updateItemQuantity(@PathVariable Long orderId, @PathVariable Long userId, @PathVariable Long itemId, @RequestBody Map<String, Integer> payload) {
        int quantity = payload.get("quantity");
        return orderService.updateItemQuantity(orderId, userId, itemId, quantity);
    }
    @PutMapping("/{orderId}/user/{userId}/complete")
    public Order completeOrder(@PathVariable Long orderId, @PathVariable Long userId) {
        return orderService.completeOrder(orderId, userId);
    }
}
