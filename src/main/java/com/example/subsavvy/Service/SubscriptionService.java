package com.example.subsavvy.Service;

import com.example.subsavvy.Data.Subscription;
import com.example.subsavvy.Repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;




@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

     public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> getSubscriptionById(UUID id) {
        return subscriptionRepository.findById(id);
    }

     public Subscription addSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(UUID id, Subscription subscription) {
        // Vérifie si l'abonnement existe
        if (subscriptionRepository.existsById(id)) {
            subscription.setId(id);
            return subscriptionRepository.save(subscription);
        } else {
            throw new RuntimeException("Sub not find");
        }
    }

    public void deleteSubscription(UUID id) {
        subscriptionRepository.deleteById(id);
    }
}
