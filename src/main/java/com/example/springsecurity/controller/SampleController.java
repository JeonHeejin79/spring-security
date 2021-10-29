package com.example.springsecurity.controller;

import com.example.springsecurity.common.CurrentUser;
import com.example.springsecurity.common.SecurityLogger;
import com.example.springsecurity.data.Account;
import com.example.springsecurity.data.UserAccount;
import com.example.springsecurity.repository.AccountRepository;
import com.example.springsecurity.repository.BookRepository;
import com.example.springsecurity.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/")
    // public String index(Model model, Principal principal) {
    // public String index(Model model, @AuthenticationPrincipal UserAccount userAccount) {
    public String index(Model model, @CurrentUser Account account) {
        // if (principal == null) {
        // if (userAccount == null) {
        if (account == null) {
            model.addAttribute("message", "Hello Sprin Security");
        } else {
            // model.addAttribute("message", "Hello, " + principal.getName());
            // model.addAttribute("message", "Hello, " + userAccount.getUsername());
            model.addAttribute("message", "Hello, " + account.getUsername());
        }
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {

        model.addAttribute("message", "Hello, " + principal.getName());
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin, " + principal.getName());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("message", "Hello User, " + principal.getName());
        model.addAttribute("books", bookRepository.findCurrentUserBooks());
        return "user";
    }

    @GetMapping("/async-handler")
    @ResponseBody
    public Callable<String> asyncHandler() {
        SecurityLogger.log("MVC");

        return () -> {
            SecurityLogger.log("Callable");
            return "Async Handler";
        };
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService() {
        SecurityLogger.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLogger.log("MVC after async service");
        return "Async Service";
    }

}
