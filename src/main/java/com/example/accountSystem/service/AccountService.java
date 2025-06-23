package com.example.accountSystem.service;

import com.example.accountSystem.Entity.Account;
import com.example.accountSystem.repository.AccountRepository;
import com.example.accountSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Account getAccountByUsername(String username)
    {
        return accountRepo.findById(username).orElseThrow(() -> new RuntimeException("Account not found"));
    }
    public Account updateStatus(String username,String status){
        Account account=accountRepo.findById(username).orElseThrow(()->new RuntimeException("Account not found!"));
        account.setStatus(status);
        account.setUpdateAt(LocalDateTime.now());
        return accountRepo.save(account);
    }
    public Account updateAccount(String username,Account updateAccount){
        Account existingAccount = accountRepo.findById(username).orElseThrow(()->new RuntimeException("Account not found!"));
        existingAccount.setStatus(updateAccount.getStatus());
        existingAccount.setStatus(updateAccount.getStatus());
        existingAccount.setUpdateAt(LocalDateTime.now());
        return accountRepo.save(existingAccount);
    }

}
