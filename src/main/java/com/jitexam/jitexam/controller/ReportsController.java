package com.jitexam.jitexam.controller;

import com.jitexam.jitexam.dto.WorkTimeByClientAndProjectReportEntry;
import com.jitexam.jitexam.dto.WorkTimeFullReportEntry;
import com.jitexam.jitexam.service.CsvMappingService;
import com.jitexam.jitexam.service.WorkTimeReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final WorkTimeReportService workTimeReportService;

    private final CsvMappingService csvMapperService;

    @GetMapping("/nameSurnameClientProject")
    public List<WorkTimeFullReportEntry> getFullReport(Pageable pageable) {
        return workTimeReportService.getWorkTimeByAccountClientProjectReport(pageable);
    }

    @RequestMapping(value = "/nameSurnameClientProject/csv", produces = "text/csv")
    public void getFullReportCsv(Pageable pageable, HttpServletResponse response) throws IOException {
        List<WorkTimeFullReportEntry> listToBeCsvMapped = workTimeReportService.getWorkTimeByAccountClientProjectReport(pageable);
        response.setCharacterEncoding("UTF-8");
        csvMapperService.mapToCsv(response.getWriter(), listToBeCsvMapped, WorkTimeFullReportEntry.class);
    }

    @GetMapping("/clientProject")
    public List<WorkTimeByClientAndProjectReportEntry> getClientProjectReport(Pageable pageable) {
        return workTimeReportService.getWorkTimeClientProjectReport(pageable);
    }

    @GetMapping("/clientProject/csv")
    public void getClientProjectReportCsv(Pageable pageable, HttpServletResponse response) throws IOException {
        List<WorkTimeByClientAndProjectReportEntry> listToBeCsvMapped =  workTimeReportService.getWorkTimeClientProjectReport(pageable);
        response.setCharacterEncoding("UTF-8");
        csvMapperService.mapToCsv(response.getWriter(), listToBeCsvMapped, WorkTimeByClientAndProjectReportEntry.class);
    }


}
