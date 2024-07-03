package com.example.shopping.service;

import com.example.shopping.model.User;
import com.example.shopping.repository.FavoriteRepository;
import com.example.shopping.repository.OrderRepository;
import com.example.shopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User authenticateUser(User user) {
        User foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            return foundUser;
        }
        throw new RuntimeException("Invalid credentials");
    }

    public User getUserDetails(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void deleteUser(Long userId) {
        try {
            logger.info("Deleting orders for userId: {}", userId);
            orderRepository.deleteByUserId(userId);

            logger.info("Deleting favorites for userId: {}", userId);
            favoriteRepository.deleteByUserId(userId);

            logger.info("Deleting user with userId: {}", userId);
            userRepository.deleteById(userId);
            logger.info("Successfully deleted user with userId: {}", userId);
        } catch (Exception e) {
            logger.error("Error deleting user with userId: " + userId, e);
            throw new RuntimeException("Error deleting user with userId: " + userId, e);
        }
    }
}
