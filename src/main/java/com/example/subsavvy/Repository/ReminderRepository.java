package com.example.subsavvy.Repository;

import com.example.subsavvy.Data.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, UUID> {

    List<Reminder> findAllByUserid(UUID userid);

    List<Reminder> findAllBySubscriptionId(UUID subscriptionId);
}
