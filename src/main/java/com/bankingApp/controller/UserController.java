package com.bankingApp.controller;

import com.bankingApp.entity.User;
import com.bankingApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    private final UserService service;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable Long userId) {
        User user = service.getUserById(userId);

        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long userId) {
        User existingUser = service.getUserById(userId);
        if (existingUser == null)
            return ResponseEntity.notFound().build();
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNo(user.getPhoneNo());
        existingUser.setAccountBalance(user.getAccountBalance());

        User updatedUser = service.updateUser(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        User user = service.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();
        service.deleteUser(userId);
        return ResponseEntity.ok().body("User deleted successfully");
    }

}
