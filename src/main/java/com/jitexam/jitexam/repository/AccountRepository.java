package com.jitexam.jitexam.repository;

import com.jitexam.jitexam.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "select * from account a join timesheetreport t on a.id = t.accountid join dailytime d2 on t.id = d2.timesheetreportid join client c on d2.clientid = c.id join project p on d2.projectid = p.id"
            , nativeQuery = true)
    List<Account> getAllData();
}
