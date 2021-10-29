package com.example.springsecurity.service;

import com.example.springsecurity.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.net.Authenticator;

@Service
public class SampleService {

//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
//    @Secured("ROLE_USER")
//    @RolesAllowed("ROLE_USER")
    @PreAuthorize("hasRole('USER')") // 메소드 실행 이전 수행
    // @PostAuthorize() // 메소드 실행이후 필요한 인증 인가 로직 수행
    public void dashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        System.out.println("=============");
        System.out.println(authentication);
        System.out.println(userDetails.getUsername());
    }

    @Async
    public void asyncService() {
        // SecurityLogger.log("Async Service");
        System.out.println("Async service is called.");
    }

}
