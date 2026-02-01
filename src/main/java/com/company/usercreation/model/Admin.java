package com.company.usercreation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    private boolean active;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // getter functions for every
    public long getId(){
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setStatus(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
}