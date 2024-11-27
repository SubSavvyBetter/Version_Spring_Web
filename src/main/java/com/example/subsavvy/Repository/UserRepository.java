package com.example.subsavvy.Repository;

import com.example.subsavvy.Data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}
