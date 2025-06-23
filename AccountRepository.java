package com.example.accountSystem.repository;
import com.example.accountSystem.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {

}
