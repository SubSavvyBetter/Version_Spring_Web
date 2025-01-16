package com.example.subsavvy.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CancellationSub {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sub_id", nullable = false)
    private Subscription subscription;

    private UUID userid;

    private LocalDateTime request_date;

    @Enumerated(EnumType.STRING)
    private Status.StatusType status;

    private LocalDateTime processed_at;
    @CreationTimestamp
    private Timestamp created_at;

    public CancellationSub(Subscription subscription, UUID userid){
        this.subscription=subscription;
        this.userid=userid;
    }

}
