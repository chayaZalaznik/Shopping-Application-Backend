// OrderService.java
package com.example.shopping.service;
import com.example.shopping.model.Order;
import com.example.shopping.model.Item;
import com.example.shopping.repository.ItemRepository;
import com.example.shopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
    public List<Order> getClosedOrdersByUserId(Long userId) {
        System.out.println("Getting all orders for user with ID: " + userId);
        List<Order> userOrders = orderRepository.findByUserId(userId); // Get all orders for the user
        System.out.println("Found " + userOrders.size() + " orders for user.");

        List<Order> closedOrders = new ArrayList<>();

        for (Order order : userOrders) {
            System.out.println("Checking order ID: " + order.getId() + ", status: " + order.getStatus());
            if ("CLOSED".equals(order.getStatus())) {
                closedOrders.add(order); // Add closed orders to the list
                System.out.println("Order ID " + order.getId() + " is CLOSED. Adding to closed orders list.");
            }
        }
		return closedOrders;
    }
    public Order createOrder(Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus("OPEN");
        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, Long userId, Order order) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!existingOrder.getUserId().equals(userId)) {
            throw new RuntimeException("User ID does not match the order's user ID");
        }

        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setTotalPrice(order.getTotalPrice());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setItems(order.getItems());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long orderId, Long userId) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!existingOrder.getUserId().equals(userId)) {
            throw new RuntimeException("User ID does not match the order's user ID");
        }

        orderRepository.deleteById(orderId);
    }

    public Order addItemToOrder(Long orderId, Long userId, Item item) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("User ID does not match the order's user ID");
        }

        // Check if the item already exists in the order
        boolean itemExists = false;
        for (Item orderItem : order.getItems()) {
            if (orderItem.getId().equals(item.getId())) {
                orderItem.setQuantity(orderItem.getQuantity() + 1);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            item.setQuantity(1); // Set initial quantity
            order.getItems().add(item);
        }

        return orderRepository.save(order);
    }

    public Order removeItemFromOrder(Long orderId, Long userId, Long itemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("User ID does not match the order's user ID");
        }

        order.getItems().removeIf(item -> item.getId().equals(itemId));
        return orderRepository.save(order);
    }
    public Order updateItemQuantity(Long orderId, Long userId, Long itemId, int quantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("User ID does not match the order's user ID");
        }

        for (Item item : order.getItems()) {
            if (item.getId().equals(itemId)) {
                item.setQuantity(quantity);
                break;
            }
        }

        return orderRepository.save(order);
    }
    public Order getOpenOrderByUserId(Long userId) {
        Order order = orderRepository.findOpenOrderByUserId(userId);
        return order; // יחזיר null אם לא נמצאה הזמנה פתוחה
    }



    public Order completeOrder(Long orderId, Long userId) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new RuntimeException("Order not found or does not belong to the user"));

        LocalDate localDate = LocalDate.now();
        System.out.println("LocalDate: " + localDate);

        order.setOrderDate(localDate); // Set the order date
        order.setStatus("CLOSED"); // Set status to CLOSED
        System.out.println("Order status set to CLOSED");

        // Calculate total price
        double totalPrice = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalPrice(totalPrice);
        System.out.println("Total price calculated: " + totalPrice);

        // Update stock for each item
        for (Item item : order.getItems()) {
            Optional<Item> itemOpt = itemRepository.findById(item.getId());
            if (itemOpt.isPresent()) {
                Item stockItem = itemOpt.get();
                stockItem.setStock(stockItem.getStock() - item.getQuantity());
                itemRepository.save(stockItem);
                System.out.println("Stock updated for item: " + stockItem.getId());
            } else {
                System.out.println("Item not found: " + item.getId());
            }
        }

        Order savedOrder = orderRepository.save(order);
        System.out.println("Order saved: " + savedOrder);
        return savedOrder;
    }
	    
}
