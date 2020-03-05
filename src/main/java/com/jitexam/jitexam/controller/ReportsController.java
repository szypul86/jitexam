package com.jitexam.jitexam.controller;

import com.jitexam.jitexam.dto.TravelReport;
import com.jitexam.jitexam.dto.WorkReport;
import com.jitexam.jitexam.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ReportService reportService;

    @GetMapping("/account/{param}")
    WorkReport getAllWorkHoursReportByAccountSorted(@PathVariable("param") String param) {
        return reportService.getAllWorkHoursReportByAccount(param);
    }

    @GetMapping("/client/{param}")
    WorkReport getAllWorkHoursReportByClientSorted(@PathVariable("param") String param) {
        return reportService.getAllWorkHoursReportByClient(param);
    }
    @GetMapping("/project/{param}")
    WorkReport getAllWorkHoursReportByProjectSorted(@PathVariable("param") String param) {
        return reportService.getAllWorkHoursReportByProject(param);
    }

    @GetMapping("/project/{param}/{surname}")
    WorkReport getAllWorkHoursReportByProjectSorted(@PathVariable("param") String param, @PathVariable("surname") String surname ) {
        return reportService.getAllWorkHoursReportByProjectBySurname(param, surname);
    }

    @GetMapping("/project/csv/{param}")
    String getCsvWorkHoursReport(@PathVariable("param") String param) throws IOException {
        return reportService.writeToCSV(param);
    }

    @GetMapping("/filteredClient/{client}/{param}")
    WorkReport getCsvWorkHoursReport(@PathVariable("param") String param,@PathVariable("client") String clientName ) throws IOException {
        return reportService.filterClientByName(clientName, param);
    }

    @GetMapping("/travel")
    TravelReport getTravelReport() {
        return reportService.getTravelReport();
    }


}
