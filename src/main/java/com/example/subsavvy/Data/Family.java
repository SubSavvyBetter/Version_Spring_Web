package com.example.subsavvy.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Family {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp update_at;

    public Family(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }
}