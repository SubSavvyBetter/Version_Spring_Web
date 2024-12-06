package com.example.subsavvy.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class CancellationSub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sub_id", nullable = false)
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime request_date;

    @Enumerated(EnumType.STRING)
    private Status.StatusType status;

    private LocalDateTime processed_at;
    private LocalDateTime created_at;

    // Getters et setters
}
