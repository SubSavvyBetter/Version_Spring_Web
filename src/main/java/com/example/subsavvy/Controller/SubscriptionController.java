package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.Subscription;
import com.example.subsavvy.Service.SubscriptionService;
import com.example.subsavvy.Security.JwtTokenProvider;
import com.example.subsavvy.Service.UserService;
import com.example.subsavvy.dto.SubscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    @GetMapping
    public List<Subscription> getAllSubscriptions(@RequestHeader("Authorization") String authorization) {
        String token = authorization.substring(7);
        String uid = jwtTokenProvider.getUserIdFromToken(token);
        UUID userId = UUID.fromString(uid);
        return subscriptionService.getSubscriptionsByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String authorization
    ) {
        String token = authorization.substring(7);
        String uid = jwtTokenProvider.getUserIdFromToken(token);
        UUID userId = UUID.fromString(uid);
        Optional<Subscription> subscription = subscriptionService.getSubscriptionById(id);
        if (subscription.isPresent() && subscription.get().getUser().equals(userId)) {
            return ResponseEntity.ok(subscription.get());
        }
        return ResponseEntity.status(403).build();
    }

    @PostMapping
    public ResponseEntity<Subscription> addSubscription(
            @RequestBody SubscriptionDto subscription,
            @RequestHeader("Authorization") String authorization
    ) {
        String token = authorization.substring(7);
        String uid = jwtTokenProvider.getUserIdFromToken(token);
        UUID userId = UUID.fromString(uid);
        Subscription addedSubscription = subscriptionService.addSubscription(userService.getUserById(userId),subscription.getName(),subscription.getPrice(),subscription.getStart_date(),subscription.getEnd_date(),subscription.isTrial(),subscription.getStatus());
        return ResponseEntity.ok(addedSubscription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(
            @PathVariable UUID id,
            @RequestBody Subscription subscription,
            @RequestHeader("Authorization") String authorization
    ) {
        String token = authorization.substring(7);
        String uid = jwtTokenProvider.getUserIdFromToken(token);
        UUID userId = UUID.fromString(uid);
        Optional<Subscription> existingSubscription = subscriptionService.getSubscriptionById(id);
        if (existingSubscription.isPresent() && existingSubscription.get().getUser().equals(userId)) {
            subscription.setUser( userService.getUserById(userId));
            Subscription updatedSubscription = subscriptionService.updateSubscription(id, subscription);
            return ResponseEntity.ok(updatedSubscription);
        }
        return ResponseEntity.status(403).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String authorization
    ) {
        String token = authorization.substring(7);
        String uid = jwtTokenProvider.getUserIdFromToken(token);
        UUID userId = UUID.fromString(uid);
        Optional<Subscription> existingSubscription = subscriptionService.getSubscriptionById(id);
        if (existingSubscription.isPresent() && existingSubscription.get().getUser().equals(userId)) {
            subscriptionService.deleteSubscription(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(403).build();
    }
}
