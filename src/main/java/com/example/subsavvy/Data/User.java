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
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String mail;
    private boolean admin;
    private String password;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp update_at;
    private String profile_picture;

    @OneToMany(mappedBy = "userid", cascade = CascadeType.ALL)
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "userid", cascade = CascadeType.ALL)
    private List<Reminder> reminders;

    @OneToMany(mappedBy = "userid", cascade = CascadeType.ALL)
    private List<CancellationSub> cancellationSubs;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    public User(String name, String mail,  String password_hash, String profile_picture, boolean admin){
        this.name =name;
        this.mail = mail;
        this.password =password_hash;
        this.profile_picture=profile_picture;
        this.admin=admin;
    }
}
