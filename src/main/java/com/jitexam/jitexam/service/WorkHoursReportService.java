package com.jitexam.jitexam.service;

import com.jitexam.jitexam.dto.WorkReport;
import com.jitexam.jitexam.repository.WorkReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkHoursReportService {

    private final WorkReportRepository workReportRepository;



    public WorkReport getAllWorkHoursReportData(){
        List<Object[]> results = workReportRepository.getAllWorkHoursReportData();
        return WorkReport.buildReport(results);
    }

    public WorkReport getAllWorkHoursReportDataSorted(String param){
        List<Object[]> results = workReportRepository.getAllWorkHoursReportDataSortedByParam( param);
        return WorkReport.buildReport(results);
    }
    public WorkReport getAllWorkHoursReportWithProjectsDataSorted(String param){
        List<Object[]> results = workReportRepository. getAllWorkHoursReportDataWithProjectSortedByParam( param);
        return WorkReport.buildReport(results);
    }

    public String writeToCSV (String param) throws IOException {
        StringWriter writer = new StringWriter();
        final CsvBeanWriter csvBeanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
        final String[] header = {"name", "surname", "client", "sum"};
        csvBeanWriter.writeHeader(header);
        getAllWorkHoursReportDataSorted(param).getEntries().forEach(
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

}
