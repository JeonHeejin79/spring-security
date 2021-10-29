package com.example.springsecurity.common;

import com.example.springsecurity.data.Account;
import com.example.springsecurity.data.Book;
import com.example.springsecurity.repository.BookRepository;
import com.example.springsecurity.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataGenerator implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // keesun - spring
        // whiteship - hibernate
        Account keesun = createUser("keesun");
        Account whiteship = createUser("whiteship");

        createBook(keesun, "spring");
        createBook(whiteship, "hibernate");
    }

    private void createBook(Account keesun, String title) {
        Book book = new Book();
        book.setAuthor(keesun);
        book.setTitle(title);

        bookRepository.save(book);
    }

    private Account createUser(String username) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword("123");
        account.setRole("USER");

        return account;
    }
}
