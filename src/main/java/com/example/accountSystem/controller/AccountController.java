package com.example.accountSystem.controller;

import com.example.accountSystem.entity.Account;
import com.example.accountSystem.reponse.AccountReponse;
import com.example.accountSystem.requests.AddAccountRequest;
import com.example.accountSystem.requests.UpdateAccountRequest;
import com.example.accountSystem.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/add")
    public ResponseEntity<AccountReponse> addAccount(@Valid @RequestBody AddAccountRequest addAccountRequest ) {
        return ResponseEntity.ok(accountService.addAccountFromRequest(addAccountRequest));
    }

    @GetMapping("/")
    public List<Account> getAll() {
        return accountService.getAll();
    }

    // ✅ Lấy account theo ID
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountReponse> updateAccount(@PathVariable Long id, @Valid @RequestBody UpdateAccountRequest request ) {
        AccountReponse reponse = accountService.updateAccount(id, request);
        return ResponseEntity.ok(reponse);
    }

    @PutMapping("/{id}/deactivate")
    public Account deactivateAccount(@PathVariable Long id) {
        return accountService.updateStatus(id, "DISABLED");
    }
}
