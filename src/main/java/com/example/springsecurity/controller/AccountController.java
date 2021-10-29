package com.example.springsecurity.controller;


import com.example.springsecurity.data.Account;
import com.example.springsecurity.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/account/{role}/{usernmae}/{password}")
    public Account createAccount(@ModelAttribute Account account) { return accountService.createNew(account); }
}
