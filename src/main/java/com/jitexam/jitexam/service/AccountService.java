package com.jitexam.jitexam.service;

import com.jitexam.jitexam.dto.WorkReportEntry;
import com.jitexam.jitexam.entity.Account;

import java.util.List;

public interface AccountService {

    public List<Account> findAll();

    List<Account> getAllData();
}
