package com.example.accountSystem.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAccountRequest {
    @NotNull(message = "system khong duoc de trong")
    private String system;
    @NotNull(message = "status khong duoc de trong")
    private String status;

}
