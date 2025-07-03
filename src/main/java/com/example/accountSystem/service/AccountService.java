package com.example.accountSystem.service;

import com.example.accountSystem.entity.Account;
import com.example.accountSystem.entity.User;
import com.example.accountSystem.reponse.AccountReponse;
import com.example.accountSystem.repository.AccountRepository;
import com.example.accountSystem.repository.UserRepository;
import com.example.accountSystem.requests.AddAccountRequest;
import com.example.accountSystem.requests.UpdateAccountRequest;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final RestClient restClient;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository, RestClient restClient) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.restClient = restClient;
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public AccountReponse addAccountFromRequest(AddAccountRequest request) {
        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username đã tồn tại: " + request.getUsername());
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user với ID: " + request.getUserId()));

        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setSystem(request.getSystem());
        account.setStatus(request.getStatus());
        account.setUser(user);

        Account saved = accountRepository.save(account);

        BankActionRequest notify = new BankActionRequest();
        notify.setAccountId(saved.getId());
        notify.setAction("ACTIVE");
        notify.setSystem(saved.getSystem());

        try {
            restClient.post()
                    .uri("http://localhost:8085/bank/mock/receive")
                    .body(notify)
                    .retrieve()
                    .toEntity(String.class);
            System.out.println(" Đã gửi thông báo tạo mới account [" + saved.getId() + "]");
        } catch (Exception e) {
            System.err.println(" Gửi thông báo thất bại: " + e.getMessage());
        }

        return new AccountReponse(saved);
    }

    public Account updateStatus(Long id, String status) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found!"));

        account.setStatus(status);
        account.setUpdateAt(LocalDateTime.now());
        Account updated = accountRepository.save(account);

        BankActionRequest request = new BankActionRequest();
        request.setAccountId(id);
        request.setAction(status);
        request.setSystem(account.getSystem());

        try {
            restClient.post()
                    .uri("http://localhost:8085/bank/mock/receive")
                    .body(request)
                    .retrieve()
                    .toEntity(String.class);
            System.out.println(" Gửi status [" + status + "] của account [" + id + "] tới mock-server thành công");
        } catch (Exception e) {
            System.err.println(" Không gửi được đến mock-server: " + e.getMessage());
        }

        return updated;
    }

    public AccountReponse updateAccount(Long id, UpdateAccountRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account không tồn tại!"));

        account.setSystem(request.getSystem());
        account.setStatus(request.getStatus());
        account.setUpdateAt(LocalDateTime.now());
        Account saved = accountRepository.save(account);

        BankActionRequest notify = new BankActionRequest();
        notify.setAccountId(saved.getId());
        notify.setAction("UPDATED");
        notify.setSystem(saved.getSystem());

        try {
            restClient.post()
                    .uri("http://localhost:8085/bank/mock/receive")
                    .body(notify)
                    .retrieve()
                    .toEntity(String.class);
            System.out.println(" Đã gửi cập nhật [" + saved.getStatus() + "] cho account [" + id + "] tới mock-server");
        } catch (Exception e) {
            System.err.println(" Không gửi được cập nhật tới mock-server: " + e.getMessage());
        }

        return new AccountReponse(saved);
    }
    @Data
    public static class BankActionRequest {
        private Long accountId;
        private String action;
        private String system;
    }
}
