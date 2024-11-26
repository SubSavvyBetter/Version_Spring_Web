package com.example.subsavvy.Data;

import java.sql.Timestamp;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String mail;
    private String password_hash;
    private Timestamp created_at;
    private Timestamp update_at;
    private String profile_picture;

    public User(String name, String password_hash, String profile_picture){
        this.id = UUID.randomUUID().toString();
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.update_at = null;
        this.name=name;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }
}
