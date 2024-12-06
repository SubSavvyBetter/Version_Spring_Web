package com.example.subsavvy.Controler;

import com.example.subsavvy.Data.User;
import com.example.subsavvy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControler {

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
}
