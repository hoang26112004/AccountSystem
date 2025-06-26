package com.example.accountSystem.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "account")
public class Account {

    @Id
    private String username;

    @Column(nullable = false)
    private String system;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @Column(length = 20)
    private String status = "ACTIVE";

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Account() {}

    public Account(String username, String system, LocalDateTime createAt, LocalDateTime updateAt, String status, User user) {
        this.username = username;
        this.system = system;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
        this.user = user;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getSystem() { return system; }

    public void setSystem(String system) { this.system = system; }

    public LocalDateTime getCreateAt() { return createAt; }

    public void setCreateAt(LocalDateTime createAt) { this.createAt = createAt; }

    public LocalDateTime getUpdateAt() { return updateAt; }

    public void setUpdateAt(LocalDateTime updateAt) { this.updateAt = updateAt; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }


    @PrePersist
    public void prePersist() {
        if (this.createAt == null) {
            this.createAt = LocalDateTime.now();
        }
        if (this.updateAt == null) {
            this.updateAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updateAt = LocalDateTime.now();
    }
}
