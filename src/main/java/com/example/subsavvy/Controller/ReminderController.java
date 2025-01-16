package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.Reminder;
import com.example.subsavvy.Data.Subscription;
import com.example.subsavvy.Security.JwtTokenProvider;
import com.example.subsavvy.Service.ReminderService;
import com.example.subsavvy.Service.SubscriptionService;
import com.example.subsavvy.dto.ReminderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(ReminderController.class);



    @PostMapping
    public ResponseEntity<Reminder> createReminder(
            @RequestBody ReminderDto reminderdto,
            @RequestParam UUID subscriptionId,
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);
        }

        String token = authorization.substring(7);

        String userId = jwtTokenProvider.getUserIdFromToken(token);

        Subscription subscription = subscriptionService.getSubscriptionById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        if (!subscription.getUserid().toString().equals(userId)) {
            return ResponseEntity.status(403).body(null);
        }
        Reminder reminder = new Reminder(subscription, UUID.fromString(userId), reminderdto.getReminder_date(), reminderdto.getMessage());

        reminder.setSubscription(subscription);
        reminder.setUserid(UUID.fromString(userId));

        Reminder createdReminder = reminderService.addReminder(reminder);

        return ResponseEntity.ok(createdReminder);
    }

    @GetMapping
    public ResponseEntity<List<Reminder>> getAllReminders(
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);
        }
        String token = authorization.substring(7);
        jwtTokenProvider.getUserIdFromToken(token);
        return ResponseEntity.ok(reminderService.getAllReminders());
    }

    @GetMapping("/user")
    public ResponseEntity<List<Reminder>> getRemindersByUserId(
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);
        }

        String token = authorization.substring(7);
        String userId = jwtTokenProvider.getUserIdFromToken(token);

        List<Reminder> reminders = reminderService.getRemindersByUserId(UUID.fromString(userId));
        return ResponseEntity.ok(reminders);
    }


    @GetMapping("/subscription/{subscriptionId}")
    public ResponseEntity<List<Reminder>> getRemindersBySubscriptionId(
            @PathVariable UUID subscriptionId,
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);
        }

        jwtTokenProvider.getUserIdFromToken(authorization.substring(7)); // Validate token
        return ResponseEntity.ok(reminderService.getRemindersBySubscriptionId(subscriptionId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);
        }

        String token = authorization.substring(7);
        String userId = jwtTokenProvider.getUserIdFromToken(token);

        Reminder reminder = reminderService.getReminderById(id);
        if (reminder != null && reminder.getUserid().toString().equals(userId)) {
            reminderService.deleteReminder(id);
            logger.info("Deleted reminder ID: {} for user ID: {}", id, userId);
            return ResponseEntity.noContent().build();
        }

        logger.warn("Unauthorized deletion attempt for reminder ID: {} by user ID: {}", id, userId);
        return ResponseEntity.status(403).body(null);
    }
}
