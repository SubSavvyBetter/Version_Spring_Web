package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.Subscription;
import com.example.subsavvy.Service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    // Endpoint to retrive all the subs
    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    // Endpoint to retrive one particular sub
    @GetMapping("/{id}")
    public Optional<Subscription> getSubscriptionById(@PathVariable UUID id) {
        return subscriptionService.getSubscriptionById(id);
    }

    // Endpoint to add sub
    @PostMapping
    public Subscription addSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.addSubscription(subscription);
    }

    // Endpoint to update sub
    @PutMapping("/{id}")
    public Subscription updateSubscription(@PathVariable UUID id, @RequestBody Subscription subscription) {
        return subscriptionService.updateSubscription(id, subscription);
    }

    // Endpoint to del sub
    @DeleteMapping("/{id}")
    public void deleteSubscription(@PathVariable UUID id) {
        subscriptionService.deleteSubscription(id);
    }
}
