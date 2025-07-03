package com.example.Bank.service;

import com.example.Bank.Entity.Account;
import com.example.Bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void updateStatus(Long id, String status) {
        Optional<Account> accOpt = accountRepository.findById(id);
        if (accOpt.isPresent()) {
            Account acc = accOpt.get();

            // ❗ CHỈ cập nhật status nếu nó hợp lệ
            if (status.equalsIgnoreCase("ACTIVE") || status.equalsIgnoreCase("DISABLED") || status.equalsIgnoreCase("LOCKED")) {
                acc.setStatus(status);
            }

            acc.setUpdatedAt(LocalDateTime.now());
            accountRepository.save(acc);
        } else {
            System.err.println("❌ Không tìm thấy account với ID: " + id);
        }
    }

}
