package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.User;
import com.example.subsavvy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to add user
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user.getName(), user.getMail(), user.getPassword_hash(), user.getPassword_hash());
    }

    // Endpoint to retreive all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser.getName(), updatedUser.getMail(), updatedUser.getPassword_hash());
    }

    // Supprimer un utilisateur par ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable User user) {
        userService.deleteUser(user);
    }
}
