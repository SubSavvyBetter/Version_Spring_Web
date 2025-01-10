package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.Subscription;
import com.example.subsavvy.Data.User;
import com.example.subsavvy.Service.SubscriptionService;
import com.example.subsavvy.Security.JwtTokenProvider;
import com.example.subsavvy.Service.UserService;
import com.example.subsavvy.dto.SubscriptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);


    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Subscription> getAllSubscriptions(@RequestHeader("Authorization") String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return (List<Subscription>) ResponseEntity.status(400).body("Invalid Authorization header");
        }
        String token = authorization.substring(7);  // Supprimer "Bearer "
        String uid = jwtTokenProvider.getUserIdFromToken(token);
        UUID userId = UUID.fromString(uid);
        return subscriptionService.getSubscriptionsByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);
        }
        String token = authorization.substring(7);
        String uid = jwtTokenProvider.getUserIdFromToken(token);
        UUID userId = UUID.fromString(uid);
        Optional<Subscription> subscription = subscriptionService.getSubscriptionById(id);
        if (subscription.isPresent() && subscription.get().getUser().getId().equals(userId)) {
            return ResponseEntity.ok(subscription.get());
        }
        return ResponseEntity.status(403).body(null);
    }

    @PostMapping
    public ResponseEntity<SubscriptionDto> addSubscription(
            @RequestBody SubscriptionDto subscription,
            @RequestHeader("Authorization") String authorization
    ) {
        logger.info("Received request to add subscription");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            logger.warn("Invalid Authorization header: {}", authorization);
            return ResponseEntity.status(400).body(null);
        }

        String token = authorization.substring(7);
        String uid = jwtTokenProvider.getUserIdFromToken(token);
        UUID userId = UUID.fromString(uid);

        logger.debug("Authorization token: {}", token);
        logger.debug("Extracted User ID from token: {}", userId);

        User user = userService.getUserById(userId);
        if (user == null) {
            logger.error("User with ID {} not found in the database", userId);
            return ResponseEntity.status(404).body(null);
        }

        logger.info("User with ID {} found. Adding subscription", userId);

        Subscription addedSubscription = subscriptionService.addSubscription(
                user, subscription.getName(), subscription.getPrice(),
                subscription.getStart_date(), subscription.getEnd_date(),
                subscription.isTrial(), subscription.getStatus()
        );

        logger.info("Subscription added successfully for user ID {}", userId);
        return ResponseEntity.ok(subscription);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(
            @PathVariable UUID id,
            @RequestBody Subscription subscription,
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);
        }
        String token = authorization.substring(7);
        String uid = jwtTokenProvider.getUserIdFromToken(token);
        UUID userId = UUID.fromString(uid);
        Optional<Subscription> existingSubscription = subscriptionService.getSubscriptionById(id);
        if (existingSubscription.isPresent() && existingSubscription.get().getUser().getId().equals(userId)) {
            subscription.setUser(userService.getUserById(userId));
            Subscription updatedSubscription = subscriptionService.updateSubscription(id, subscription);
            return ResponseEntity.ok(updatedSubscription);
        }
        return ResponseEntity.status(403).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);
        }
        String token = authorization.substring(7);
        String uid = jwtTokenProvider.getUserIdFromToken(token);
        UUID userId = UUID.fromString(uid);
        Optional<Subscription> existingSubscription = subscriptionService.getSubscriptionById(id);
        if (existingSubscription.isPresent() && existingSubscription.get().getUser().getId().equals(userId)) {
            subscriptionService.deleteSubscription(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(403).body(null);
    }
}
