package com.example.accountSystem.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account {
    @Id
    private String username;
    @Column(nullable = false)
    private String system;
    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "update_at")
    private LocalDateTime updateAt = LocalDateTime.now();
    @Column(length = 20)
    private String status = "ACTIVE";

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    public String getSystem() {
        return system;
    }

    public Account() {
    }

    public Account(String system, LocalDateTime createAt, LocalDateTime updateAt, String status, String username, User user) {
        this.system = system;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
        this.username = username;
        this.user = user;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
