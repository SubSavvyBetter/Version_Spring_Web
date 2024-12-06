package com.example.subsavvy.Repository;

import com.example.subsavvy.Data.Subscription;
import com.example.subsavvy.Data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}
