package com.example.accountSystem.reponse;

import com.example.accountSystem.entity.User;
import lombok.Data;

@Data
public class UserReponse {
    private Integer id;
    private String email;
    private String name;
    private String phone;
    public UserReponse(User user) {
        this.id=user.getId();
        this.email=user.getEmail();
        this.name=user.getName();
        this.phone=user.getPhone();
    }
}
