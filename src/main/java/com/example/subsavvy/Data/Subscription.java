package com.example.subsavvy.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Subscription {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;



    public Subscription(String name, String password_hash, String profile_picture){
        this.name=name;
    }
}
