package com.example.accountSystem.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
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

    public Account(Long id,String username, String system, LocalDateTime createAt, LocalDateTime updateAt, String status, User user) {
        this.id = id;
        this.username = username;
        this.system = system;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
        this.user = user;
    }
    @PrePersist
    public void prePersist() {
        if (this.createAt == null) {
            this.createAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updateAt = LocalDateTime.now();
    }
}
