package com.example.subsavvy.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
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

    private UUID creator_id;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> userList = new ArrayList<>();

    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp update_at;

    public Family(String name, UUID creator_id) {
        this.name = name;
        this.creator_id = creator_id;
    }

    public void addMember(User user){
        this.userList.add(user);
    }
}