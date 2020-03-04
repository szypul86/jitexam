package com.jitexam.jitexam.repository;

import com.jitexam.jitexam.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
