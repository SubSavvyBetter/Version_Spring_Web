package com.example.subsavvy.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sub_id", nullable = false)
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate reminder_date;
    private String message;

    @Enumerated(EnumType.STRING)
    private Status.StatusType status;

    private LocalDateTime created_at;
    private LocalDateTime end_at;

    // Getters et setters
}
