package com.example.accountSystem.requests;

import com.example.accountSystem.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddAccountRequest {
   @NotBlank(message = "username khong duoc bo trong")
    private String username;

   @NotBlank(message = "system khong duoc bo trong")
    private String system;

   private String status="ACTIVE";

   @NotNull(message = "User Id k duoc bo trong")
    private Integer UserId;
}





