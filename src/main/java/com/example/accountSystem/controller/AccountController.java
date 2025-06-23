package com.example.accountSystem.controller;

import com.example.accountSystem.Entity.Account;
import com.example.accountSystem.service.AccountService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/add")
    public Account add(@RequestBody Account account) {
        return accountService.addAccount(account);
    }
    @GetMapping("/")
    public List<Account> getAll() {
        return accountService.getAll();
    }
    
}
