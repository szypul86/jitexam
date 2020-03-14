package com.jitexam.jitexam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitexam.jitexam.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ReportsControllerTest {

    @Autowired
    ReportRepository reportRepository;


    @Test
    void getAllWorkHoursReportByAccountSorted() {

    }

    @Test
    void getAllWorkHoursReportByClientSorted() {
    }

    @Test
    void getAllWorkHoursReportByProjectSorted() {

    }

    @Test
    void getCsvWorkHoursReport() {
    }

    @Test
    void getTravelReport() {
    }
}