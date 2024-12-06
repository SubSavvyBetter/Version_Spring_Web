package com.example.subsavvy.Repository;

import com.example.subsavvy.Data.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, UUID> {
    // Méthode pour récupérer les rappels par userId
    List<Reminder> findAllByUserId(UUID userId);

    // Méthode pour récupérer les rappels par subscriptionId
    List<Reminder> findAllBySubscriptionId(UUID subscriptionId);
}
