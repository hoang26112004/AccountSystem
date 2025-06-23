package com.example.accountSystem.service;

import com.example.accountSystem.Entity.Account;
import com.example.accountSystem.repository.AccountRepository;
import com.example.accountSystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepo;
    public AccountService(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }
    public Account addAccount(Account account) {
        return accountRepo.save(account);
    }
    public List<Account> getAll()
    {
        return accountRepo.findAll();
    }

}
