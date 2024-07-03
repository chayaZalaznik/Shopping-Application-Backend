package com.example.shopping.controller;

import com.example.shopping.model.User;
import com.example.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            User authenticatedUser = userService.authenticateUser(user);
            return ResponseEntity.ok(authenticatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
        try {
            User userDetails = userService.getUserDetails(userId);
            return ResponseEntity.ok(userDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        try {
        	System.out.println(userId);
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
