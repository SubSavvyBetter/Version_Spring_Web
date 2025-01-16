package com.example.subsavvy.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Reminder {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sub_id", nullable = false)
    private Subscription subscription;

    private UUID userid;

    private LocalDate reminder_date;
    private String message;

    @Enumerated(EnumType.STRING)
    private Status.StatusType status;

    @CreationTimestamp
    private Timestamp created_at;
    private LocalDateTime end_at;

    public Reminder(Subscription subscription, UUID userid, LocalDate reminder_date, String message){
        this.subscription=subscription;
        this.userid=userid;
        this.reminder_date=reminder_date;
        this.message=message;
    }


}
