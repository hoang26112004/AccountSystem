package com.example.accountSystem.reponse;
import com.example.accountSystem.entity.Account;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountReponse {
    private Long id;
    private String username;
    private String system;
    private String status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public AccountReponse(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.system = account.getSystem();
        this.status = account.getStatus();
        this.createAt = account.getCreateAt();
        this.updateAt = account.getUpdateAt();

        if (account.getUpdateAt() != null) {
            this.updateAt = account.getUpdateAt();
        }
    }
}
