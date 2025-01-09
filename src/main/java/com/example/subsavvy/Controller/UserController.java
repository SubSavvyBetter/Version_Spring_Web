package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.User;
import com.example.subsavvy.Security.JwtTokenProvider;
import com.example.subsavvy.Service.UserService;
import com.example.subsavvy.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    // Injection via le constructeur
    public UserController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Endpoint to add user
    @PostMapping
    public User addUser(@RequestBody UserDto user) {
        return userService.addUser(user.getName(), user.getMail(), user.getPassword(), user.getPassword() , user.isAdmin());
    }

    // Endpoint to retreive all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser.getName(), updatedUser.getMail(), updatedUser.getPassword());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable User user) {
        userService.deleteUser(user);
    }

    @GetMapping("/api/user/uid")
    public ResponseEntity<String> getUid(@RequestHeader("Authorization") String authorization) {
        // Extraire le token Bearer
        String token = authorization.substring(7);

        String uid = jwtTokenProvider.getUserIdFromToken(token);

        return ResponseEntity.ok(uid);  // Retourner l'UID dans la réponse
    }
}
