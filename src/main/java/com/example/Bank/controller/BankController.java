package com.example.Bank.controller;

import com.example.Bank.service.AccountService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank/mock")
public class BankController {

    private final AccountService accountService;

    public BankController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/receive")
    public ResponseEntity<String> receive(@RequestBody BankActionRequest request) {
//        accountService.updateStatus(request.getAccountId(), request.getAction()); // ✅ dùng ID

        switch (request.getAction().toUpperCase()) {
            case "ACTIVE" -> System.out.println(" Đã tạo mới tài khoản id: " + request.getAccountId());
            case "DISABLED" -> System.out.println(" Đã khóa tài khoản id: " + request.getAccountId());
            case "UPDATED" -> System.out.println(" Đã cập nhật tài khoản id: " + request.getAccountId());
            default -> System.out.println(" Nhận trạng thái [" + request.getAction() + "] cho account: " + request.getAccountId());
        }

        return ResponseEntity.ok("Đã nhận trạng thái: " + request.getAction());
    }
    @Data
    public static class BankActionRequest {
        private Long accountId; //  PHẢI là Long
        private String action;
        private String system;
    }

}
