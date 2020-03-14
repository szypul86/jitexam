package com.jitexam.jitexam.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    /*@Mock
    ReportRepository reportRepository;

    @InjectMocks
    ReportService reportService;


    @Test
    void getAllWorkHoursReportByAccount() {
        String param = "name";
        List<Object[]> objectsList =  List.of(new Object[]{"Adam", "Miauczynski",BigDecimal.valueOf(123450000), "McDonalds", "Best Big Mac"},
                new Object[]{"Zenon", "Zorro",BigDecimal.valueOf(999950000), "ZTM", "Zaradzic zarazkom"});

        when(reportRepository.getAllWorkHoursReportByAccountSortedByParam(param)).thenReturn(objectsList);

        WorkReport workReport = reportService.getAllWorkHoursReportByAccount(param);
        assertEquals(workReport.getEntries().size(),2);
        assertNotNull(workReport.getEntries().get(0).getName());
    }

    @Test
    void getAllWorkHoursReportByClient() {
        String param = "name";
        List<Object[]> objectsList =  List.of(new Object[]{"Adam", "Miauczynski",BigDecimal.valueOf(123450000), "McDonalds", "Best Big Mac"},
                new Object[]{"Zenon", "Zorro",BigDecimal.valueOf(999950000), "ZTM", "Zaradzic zarazkom"});

        when(reportRepository.getAllWorkHoursReportByClientSortedByParam(param)).thenReturn(objectsList);

        WorkReport workReport = reportService.getAllWorkHoursReportByClient(param);
        assertEquals(workReport.getEntries().size(),2);
        assertNotNull(workReport.getEntries().get(0).getName());
    }


   *//* @Test
    void writeToCSV() throws IOException {
        String param = "name";
        List<Object[]> objectsList =  List.of(new Object[]{"Adam", "Miauczynski",BigDecimal.valueOf(123450000), "McDonalds", "Best Big Mac"},
                new Object[]{"Zenon", "Zorro",BigDecimal.valueOf(999950000), "ZTM", "Zaradzic zarazkom"});
        when( reportRepository.getAllWorkHoursReportDataByProjectSortedByParam(param)).thenReturn(objectsList);
        String workReport = reportService.writeToCSV(param);
        assertEquals(workReport.split("[,\\n]").length,15);
        assertEquals(workReport.split("[,\\n]")[5],"Adam");
    }*//*

    @Test
    void filterClientByName() {
        String param = "name";
        List<Object[]> objectsList =  List.of(new Object[]{"Adam", "Miauczynski",BigDecimal.valueOf(123450000), "McDonalds", "Best Big Mac"},
                new Object[]{"Zenon", "Zorro",BigDecimal.valueOf(999950000), "ZTM", "Zaradzic zarazkom"});

        when(reportRepository.getAllWorkHoursReportByClientSortedByParam(param)).thenReturn(objectsList);

        WorkReport workReport = reportService.filterClientByName("ZTM", param);
        assertEquals(workReport.getEntries().size(),1);
        assertEquals(workReport.getEntries().get(0).getName(),"Zenon");
    }*/
}