package com.jitexam.jitexam.service;

import com.jitexam.jitexam.dto.TravelReport;
import com.jitexam.jitexam.dto.WorkReport;
import com.jitexam.jitexam.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public WorkReport getAllWorkHoursReportByAccount(String param) {
        List<Object[]> results = reportRepository.getAllWorkHoursReportByAccountSortedByParam(param);
        return WorkReport.buildReport(results);
    }

    public WorkReport getAllWorkHoursReportByClient(String param) {
        List<Object[]> results = reportRepository.getAllWorkHoursReportByClientSortedByParam(param);
        return WorkReport.buildReport(results);
    }

    public WorkReport getAllWorkHoursReportByProject(String param) {
        List<Object[]> results = reportRepository.getAllWorkHoursReportDataByProjectSortedByParam(param);
        return WorkReport.buildReport(results);
    }

    public WorkReport getAllWorkHoursReportByProjectBySurname(String param, String surname) {
        List<Object[]> results = reportRepository.getAllWorkHoursReportByProjectBySurname(param, surname);
        return WorkReport.buildReport(results);
    }

    public TravelReport getTravelReport() {
        return TravelReport.buildReport(reportRepository.getTravelReport());
    }

    public String writeToCSV(String param) throws IOException {
        StringWriter writer = new StringWriter();
        final CsvBeanWriter csvBeanWriter = new CsvBeanWriter(writer, new CsvPreference.Builder('|', ',', "\n").build());
        final String[] header = {"name", "surname", "client", "project", "sum"};
        csvBeanWriter.writeHeader(header);
        getAllWorkHoursReportByProject(param).getEntries().forEach(
                (entry) -> {
                    try {
                        csvBeanWriter.write(entry, header);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        csvBeanWriter.close();
        return writer.toString();
    }


    public WorkReport filterProjectByName(String projectName, String param) {
        return new WorkReport(
                getAllWorkHoursReportByProject(param).getEntries().stream()
                        .filter(e -> e.getProject().equals(projectName))
                        .collect(Collectors.toList()));
    }

    public WorkReport filterClientByName(String clientName, String param) {
        return new WorkReport(
                getAllWorkHoursReportByClient(param).getEntries().stream()
                        .filter(e -> e.getClient().equals(clientName))
                        .collect(Collectors.toList()));
    }
}
