package com.jitexam.jitexam.service;

import com.jitexam.jitexam.dto.WorkReport;
import com.jitexam.jitexam.entity.Account;
import com.jitexam.jitexam.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getAllData() {
        return accountRepository.getAllData();
    }

}
