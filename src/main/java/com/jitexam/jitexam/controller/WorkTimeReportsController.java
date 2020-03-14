package com.jitexam.jitexam.controller;

import com.jitexam.jitexam.dto.WorkTimeByAccountClientProjectReportEntry;
import com.jitexam.jitexam.dto.WorkTimeByClientProjectReportEntry;
import com.jitexam.jitexam.service.CsvMappingService;
import com.jitexam.jitexam.service.WorkTimeReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reports/workTime")
@RequiredArgsConstructor
public class WorkTimeReportsController {

    private final WorkTimeReportService workTimeReportService;
    private final CsvMappingService csvMapperService;

    @GetMapping("/nameSurnameClientProject")
    public List<WorkTimeByAccountClientProjectReportEntry> getFullReport(Pageable pageable,
                                                                         @RequestParam(required = false, defaultValue = "all") String name,
                                                                         @RequestParam(required = false, defaultValue = "all") String surname,
                                                                         @RequestParam(required = false, defaultValue = "all") String client,
                                                                         @RequestParam(required = false, defaultValue = "all") String project) {
        return workTimeReportService.getWorkTimeByAccountClientProjectReport(pageable, new String[]{name, surname, client, project});
    }

    @RequestMapping(value = "/nameSurnameClientProject/csv", produces = "text/csv")
    public void getFullReportCsv(Pageable pageable, HttpServletResponse response,
                                 @RequestParam(required = false, defaultValue = "all") String name,
                                 @RequestParam(required = false, defaultValue = "all") String surname,
                                 @RequestParam(required = false, defaultValue = "all") String client,
                                 @RequestParam(required = false, defaultValue = "all") String project) throws IOException {
        List<WorkTimeByAccountClientProjectReportEntry> listToBeCsvMapped = workTimeReportService.getWorkTimeByAccountClientProjectReport(pageable, new String[]{name, surname, client, project});
        response.setCharacterEncoding("UTF-8");
        csvMapperService.mapToCsv(response.getWriter(), listToBeCsvMapped, WorkTimeByAccountClientProjectReportEntry.class, new String[]{"name", "surname", "client", "project", "hours"});
    }

    @GetMapping("/nameSurnameClient")
    public List<WorkTimeByAccountClientProjectReportEntry> getWorkTimeByAccountClientReport(Pageable pageable,
                                                                                            @RequestParam(required = false, defaultValue = "all") String name,
                                                                                            @RequestParam(required = false, defaultValue = "all") String surname,
                                                                                            @RequestParam(required = false, defaultValue = "all") String client) {
        return workTimeReportService.getWorkTimeByAccountClientReport(pageable, new String[]{name, surname, client});
    }

    @RequestMapping(value = "/nameSurnameClient/csv", produces = "text/csv")
    public void getWorkTimeByAccountClientReportCsv(Pageable pageable, HttpServletResponse response,
                                                    @RequestParam(required = false, defaultValue = "all") String name,
                                                    @RequestParam(required = false, defaultValue = "all") String surname,
                                                    @RequestParam(required = false, defaultValue = "all") String client) throws IOException {
        List<WorkTimeByAccountClientProjectReportEntry> listToBeCsvMapped = workTimeReportService.getWorkTimeByAccountClientReport(pageable, new String[]{name, surname, client});
        response.setCharacterEncoding("UTF-8");
        csvMapperService.mapToCsv(response.getWriter(), listToBeCsvMapped, WorkTimeByAccountClientProjectReportEntry.class, new String[]{"name", "surname", "client", "hours"});
    }

    @GetMapping("/nameSurname")
    public List<WorkTimeByAccountClientProjectReportEntry> getWorkTimeByAccountReport(Pageable pageable,
                                                                                      @RequestParam(required = false, defaultValue = "all") String name,
                                                                                      @RequestParam(required = false, defaultValue = "all") String surname) {
        return workTimeReportService.getWorkTimeByAccountReport(pageable, new String[]{name, surname});
    }

    @RequestMapping(value = "/nameSurname/csv", produces = "text/csv")
    public void getWorkTimeByAccountReportCsv(Pageable pageable, HttpServletResponse response,
                                              @RequestParam(required = false, defaultValue = "all") String name,
                                              @RequestParam(required = false, defaultValue = "all") String surname) throws IOException {
        List<WorkTimeByAccountClientProjectReportEntry> listToBeCsvMapped = workTimeReportService.getWorkTimeByAccountReport(pageable, new String[]{name, surname});
        response.setCharacterEncoding("UTF-8");
        csvMapperService.mapToCsv(response.getWriter(), listToBeCsvMapped, WorkTimeByAccountClientProjectReportEntry.class, new String[]{"name", "surname", "hours"});
    }


    @GetMapping("/clientProject")
    public List<WorkTimeByClientProjectReportEntry> getClientProjectReport(Pageable pageable,
                                                                           @RequestParam(required = false, defaultValue = "all") String client,
                                                                           @RequestParam(required = false, defaultValue = "all") String project) {
        return workTimeReportService.getWorkTimeClientProjectReport(pageable, new String[]{client, project});
    }

    @GetMapping("/clientProject/csv")
    public void getClientProjectReportCsv(Pageable pageable, HttpServletResponse response,
                                          @RequestParam(required = false, defaultValue = "all") String client,
                                          @RequestParam(required = false, defaultValue = "all") String project) throws IOException {
        List<WorkTimeByClientProjectReportEntry> listToBeCsvMapped = workTimeReportService.getWorkTimeClientProjectReport(pageable, new String[]{client, project});
        response.setCharacterEncoding("UTF-8");
        csvMapperService.mapToCsv(response.getWriter(), listToBeCsvMapped, WorkTimeByClientProjectReportEntry.class, new String[]{"client", "project", "hours"});
    }

    @GetMapping("/client")
    public List<WorkTimeByClientProjectReportEntry> getClientReport(Pageable pageable,
                                                                    @RequestParam(required = false, defaultValue = "all") String client) {
        return workTimeReportService.getWorkTimeByClientReport(pageable, new String[]{client});
    }

    @GetMapping("/client/csv")
    public void getClientReportCsv(Pageable pageable, HttpServletResponse response,
                                   @RequestParam(required = false, defaultValue = "all") String client) throws IOException {
        List<WorkTimeByClientProjectReportEntry> listToBeCsvMapped = workTimeReportService.getWorkTimeByClientReport(pageable, new String[]{client});
        response.setCharacterEncoding("UTF-8");
        csvMapperService.mapToCsv(response.getWriter(), listToBeCsvMapped, WorkTimeByClientProjectReportEntry.class, new String[]{"client", "hours"});
    }


}
