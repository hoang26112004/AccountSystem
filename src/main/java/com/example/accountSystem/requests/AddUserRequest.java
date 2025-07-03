package com.example.accountSystem.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddUserRequest {
    @NotNull(message = "Id khong duoc bo trong")
    private Integer id;

    @NotBlank(message = "Email khong duoc de trong")
    @Email(message = "email khong hop le")
    private String email;

    @NotBlank(message = "ten khong duoc de trong")
    private String name;

    private String phone;
}
