package com.example.springsecurity.service;


import com.example.springsecurity.data.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
public class SampleServiceTest {


    @Autowired
    SampleService sampleService;

    @Autowired
    AccountService accountService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Test
    public void dashboard() {

        Account account = new Account();
        account.setRole("USER");
        account.setUsernmae("keesun");
        account.setPassword("123");

        accountService.createNew(account);

        UserDetails userDetails = accountService.loadUserByUsername("keesun");

        // principal : 유저객체 , credential : 사용자가 입력한 비밀번호
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, "123");

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        sampleService.dashboard();
    }

    @Test
    @WithMockUser
    public void dashboard2() {
        sampleService.dashboard();
    }

}
