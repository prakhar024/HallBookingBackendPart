package com.example.hallbooking.controller;

import com.example.hallbooking.model.User;
import com.example.hallbooking.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }
    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userRepo.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            user.setPassword(userDetails.getPassword());
            userRepo.save(user);
            return ResponseEntity.ok(user);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!userRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
//@RestController
//@RequestMapping("/api/v1/users")
//@CrossOrigin(origins = "http://localhost:4200")
//public class UserController {
//
//    private final UserRepository userRepo;
//
//    public UserController(UserRepository userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userRepo.findAll();
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> signup(@RequestBody User user) {
//        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
//
//        if (existingUser.isPresent()) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists!");
//        }
//
//        User savedUser = userRepo.save(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
//
//        if (existingUser.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
//        }
//
//        User dbUser = existingUser.get();
//        if (!dbUser.getPassword().equals(user.getPassword())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password!");
//        }
//
//        return ResponseEntity.ok("Login successful!");
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
//        return userRepo.findById(id).map(user -> {
//            user.setName(userDetails.getName());
//            user.setEmail(userDetails.getEmail());
//            user.setPhone(userDetails.getPhone());
//            user.setPassword(userDetails.getPassword());
//            userRepo.save(user);
//            return ResponseEntity.ok(user);
//        }).orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//        if (!userRepo.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        userRepo.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
//}
