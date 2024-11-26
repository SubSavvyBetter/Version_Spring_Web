package com.example.subsavvy.Repository;

import com.example.subsavvy.Data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(String id);
    User saveUser(User usertoadd);
    void deleteUser(User usertodel);
    List<User> getAllUsers();
}
