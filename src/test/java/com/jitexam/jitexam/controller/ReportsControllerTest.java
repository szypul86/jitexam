package com.jitexam.jitexam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitexam.jitexam.repository.ReportRepository;
import com.jitexam.jitexam.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.web.servlet.MockMvc;

@DataJpaTest
class ReportsControllerTest {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    ReportService reportService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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