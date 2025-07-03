package com.example.accountSystem.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private Integer id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String name;
    private String phone;
    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Account> accounts;

    public User() {
    }

    public User(Integer id, String email, String name, String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}
