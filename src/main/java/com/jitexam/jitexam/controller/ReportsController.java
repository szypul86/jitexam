package com.jitexam.jitexam.controller;

import com.jitexam.jitexam.dto.WorkReport;
import com.jitexam.jitexam.service.WorkHoursReportService;
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

    private final WorkHoursReportService workHoursReportService;

    @GetMapping("/{param}")
    WorkReport getAllWorkHoursReportDataSorted(@PathVariable("param") String param) {
        return workHoursReportService.getAllWorkHoursReportDataSorted(param);
    }
    @GetMapping("/withProjects/{param}")
    WorkReport getAllWorkHoursReportWithProjectDataSorted(@PathVariable("param") String param) {
        return workHoursReportService.getAllWorkHoursReportWithProjectsDataSorted(param);
    }

    @GetMapping("csv/{param}")
    String getCsvWorkHoursReport(@PathVariable("param") String param) throws IOException {
        return workHoursReportService.writeToCSV(param);
    }


}
