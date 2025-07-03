package com.example.Bank.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String system;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(length = 20)
    private String status = "ACTIVE";

    @Column(name = "user_id")
    private Integer userId;

    public Account() {
    }

    public Account(Long id, String username, String system, LocalDateTime createdAt, LocalDateTime updatedAt, String status, Integer userId) {
        this.id = id;
        this.username = username;
        this.system = system;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.userId = userId;
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
        if (this.updatedAt == null) this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
