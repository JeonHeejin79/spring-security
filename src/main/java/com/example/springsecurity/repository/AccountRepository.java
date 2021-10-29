package com.example.springsecurity.repository;

import com.example.springsecurity.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AccountRepository extends JpaRepository<Account, String> {

    public Account findByUsername(String username);

    public Account save(Account account);
}
