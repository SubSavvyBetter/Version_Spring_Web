package com.example.subsavvy.Service;
import com.example.subsavvy.Data.User;
import com.example.subsavvy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
