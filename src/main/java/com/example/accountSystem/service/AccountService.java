//package com.example.accountSystem.service;
//
//import com.example.accountSystem.Entity.Account;
//import com.example.accountSystem.repository.AccountRepository;
//import com.example.accountSystem.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AccountService {
//    private final AccountRepository accountRepo;
//    public AccountService(AccountRepository accountRepo) {
//        this.accountRepo = accountRepo;
//    }
//    public Account addAccount(Account account) {
//        return accountRepo.save(account);
//    }
//
//    public List<Account> getAll()
//    {
//        return accountRepo.findAll();
//    }
//    public Account getAccountByUsername(String username)
//    {
//        return accountRepo.findById(username).orElseThrow(() -> new RuntimeException("Account not found"));
//    }
//    public Account updateStatus(String username,String status){
//        Account account=accountRepo.findById(username).orElseThrow(()->new RuntimeException("Account not found!"));
//        account.setStatus(status);
//        account.setUpdateAt(LocalDateTime.now());
//        return accountRepo.save(account);
//    }
//    public Account updateAccount(String username,Account updateAccount){
//        Account existingAccount = accountRepo.findById(username).orElseThrow(()->new RuntimeException("Account not found!"));
//        existingAccount.setStatus(updateAccount.getStatus());
//        existingAccount.setSystem(updateAccount.getSystem());
//        existingAccount.setUpdateAt(LocalDateTime.now());
//        return accountRepo.save(existingAccount);
//    }
//
//}
package com.example.accountSystem.service;

import com.example.accountSystem.Entity.Account;
import com.example.accountSystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepo;

    @Autowired
    private RestTemplate restTemplate;

    public AccountService(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account addAccount(Account account) {
        Account saved = accountRepo.save(account);

        // 📨 Gửi thông báo "tạo mới" sang mock-server
        BankActionRequest request = new BankActionRequest();
        request.setAccountId(saved.getUsername());
        request.setAction("ACTIVE");
        request.setSystem(saved.getSystem());

        try {
            restTemplate.postForEntity("http://localhost:8085/bank/mock/receive", request, String.class);
            System.out.println("✅ Đã gửi thông báo tạo mới cho account [" + saved.getUsername() + "] tới mock-server");
        } catch (Exception e) {
            System.err.println("❌ Gửi thông báo tạo mới thất bại: " + e.getMessage());
        }

        return saved;
    }

    public List<Account> getAll() {
        return accountRepo.findAll();
    }

    public Account getAccountByUsername(String username) {
        return accountRepo.findById(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account updateStatus(String username, String status) {
        Account account = accountRepo.findById(username)
                .orElseThrow(() -> new RuntimeException("Account not found!"));
        account.setStatus(status);
        account.setUpdateAt(LocalDateTime.now());
        Account updated = accountRepo.save(account);

        // 📨 Gửi thông báo cập nhật trạng thái sang mock-server
        BankActionRequest request = new BankActionRequest();
        request.setAccountId(username);
        request.setAction(status);
        request.setSystem(account.getSystem());

        try {
            restTemplate.postForEntity("http://localhost:8085/bank/mock/receive", request, String.class);
            System.out.println("✅ Gửi status [" + status + "] của account [" + username + "] tới mock-server thành công");
        } catch (Exception e) {
            System.err.println("❌ Không gửi được đến mock-server: " + e.getMessage());
        }

        return updated;
    }

    public Account updateAccount(String username, Account updateAccount) {
        Account existingAccount = accountRepo.findById(username)
                .orElseThrow(() -> new RuntimeException("Account not found!"));
        existingAccount.setStatus(updateAccount.getStatus());
        existingAccount.setSystem(updateAccount.getSystem());
        existingAccount.setUpdateAt(LocalDateTime.now());
        return accountRepo.save(existingAccount);
    }

    // 👇 DTO để gửi sang mock-server
    public static class BankActionRequest {
        private String accountId;
        private String action;
        private String system;

        public String getAccountId() { return accountId; }
        public void setAccountId(String accountId) { this.accountId = accountId; }

        public String getAction() { return action; }
        public void setAction(String action) { this.action = action; }

        public String getSystem() { return system; }
        public void setSystem(String system) { this.system = system; }
    }
}

