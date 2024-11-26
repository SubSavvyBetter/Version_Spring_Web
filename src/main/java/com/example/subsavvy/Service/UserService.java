package com.example.subsavvy.Service;
import com.example.subsavvy.Data.User;
import com.example.subsavvy.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

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

    public User createUser(User user) {
        return new User(user.getName(), user.getPassword_hash(), user.getProfile_picture());
    }

}
