package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.CancellationSub;
import com.example.subsavvy.Data.Reminder;
import com.example.subsavvy.Data.Subscription;
import com.example.subsavvy.Security.JwtTokenProvider;
import com.example.subsavvy.Service.CancellationSubService;
import com.example.subsavvy.Service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cancellations")
public class CancellationSubController {

    @Autowired
    private CancellationSubService cancellationSubService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<CancellationSub> createCancellation(
                @RequestParam UUID subscriptionId ,
                @RequestHeader("Authorization") String authorization
    ){
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
        CancellationSub cancellationSub = new CancellationSub(subscription, UUID.fromString(userId));

        cancellationSubService.addCancellation(cancellationSub);
        return ResponseEntity.ok(cancellationSub);
    }

    @GetMapping
    public List<CancellationSub> getAllCancellations() {
        return cancellationSubService.getAllCancellations();
    }


    @GetMapping("/user")
    public ResponseEntity<List<CancellationSub>> getCancellationsByUserId(
            @RequestHeader("Authorization") String authorization)
    {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);
        }

        String token = authorization.substring(7);

        String userId = jwtTokenProvider.getUserIdFromToken(token);

        return ResponseEntity.ok(cancellationSubService.getCancellationsByUserId(UUID.fromString(userId)));
    }

    @DeleteMapping("/{id}")
    public void deleteCancellation(@PathVariable UUID id) {
        cancellationSubService.deleteCancellation(id);
    }
}
