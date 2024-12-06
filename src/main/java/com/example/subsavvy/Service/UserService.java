package com.example.subsavvy.Service;
import com.example.subsavvy.Data.User;
import com.example.subsavvy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User addUser(String name, String mail,  String password_hash, String profile_picture) {
        User user = new User(name, mail, password_hash, profile_picture);
        return userRepository.save(user); // Sauvegarde dans la base de données
    }

    public User updateUser(UUID id, String name, String mail, String passwordHash) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        existingUser.setName(name);
        existingUser.setMail(mail);
        existingUser.setPassword_hash(passwordHash);

        return userRepository.save(existingUser);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

}
