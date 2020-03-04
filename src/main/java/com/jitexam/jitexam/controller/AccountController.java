package com.jitexam.jitexam.controller;

import com.jitexam.jitexam.entity.Account;
import com.jitexam.jitexam.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public List<Account> getAllData() {
        return accountService.getAllData();
    }

}
