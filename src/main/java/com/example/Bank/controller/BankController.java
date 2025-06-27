package com.example.Bank.controller;

import com.example.Bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank/mock")
public class BankController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/receive")
    public ResponseEntity<String> receive(@RequestBody BankActionRequest request) {
        String username = request.getAccountId();
        String status = request.getAction();
        String system = request.getSystem();

        accountService.updateSatus(username, status);

        // ✅ Log rõ theo hành động
        switch (status.toUpperCase()) {
            case "ACTIVE" -> System.out.println("✅ Đã nhận tài khoản mới: " + username + " (Hệ thống: " + system + ")");
            case "DISABLED" -> System.out.println("🔒 Đã khóa tài khoản: " + username);
            case "DISCONNECTED" -> System.out.println("🔌 Đã ngắt tài khoản: " + username);
            default -> System.out.println("🔁 Đã cập nhật trạng thái [" + status + "] cho tài khoản: " + username);
        }

        return ResponseEntity.ok("✅ Đã cập nhật trạng thái [" + status + "] cho tài khoản: " + username);
    }

    // ✅ Lớp nhận JSON từ accountSystem
    public static class BankActionRequest {
        private String accountId;
        private String action;
        private String system;

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }
    }
}
