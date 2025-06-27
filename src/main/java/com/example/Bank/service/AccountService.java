package com.example.Bank.service;

import com.example.Bank.Entity.Account;
import com.example.Bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public void updateSatus(String username, String satus) {
        Optional<Account> accOpt = accountRepository.findById(username);
        if (accOpt.isPresent()) {
            Account acc = accOpt.get();
            acc.setStatus(satus);
            acc.setUpdateAt(LocalDateTime.now());
            accountRepository.save(acc);


            System.out.println("Đã khóa ngắt username: " + username);
        } else {
            System.out.println("Không tìm thấy account với username: " + username);
        }
    }

}
