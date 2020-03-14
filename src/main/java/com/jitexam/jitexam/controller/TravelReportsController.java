package com.jitexam.jitexam.controller;

import com.jitexam.jitexam.dto.TravelByClientProjectReportEntry;
import com.jitexam.jitexam.dto.TravelReportEntry;
import com.jitexam.jitexam.service.CsvMappingService;
import com.jitexam.jitexam.service.TravelReportService;
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
@RequestMapping("/reports/travel")
@RequiredArgsConstructor
public class TravelReportsController {

    private final TravelReportService travelReportService;

    private final CsvMappingService csvMapperService;

    @GetMapping("/nameSurnameClientProjectReservationBy")
    public List<TravelReportEntry> getFullReport(Pageable pageable,
                                                 @RequestParam(required = false, defaultValue = "all") String name,
                                                 @RequestParam(required = false, defaultValue = "all") String surname,
                                                 @RequestParam(required = false, defaultValue = "all") String client,
                                                 @RequestParam(required = false, defaultValue = "all") String project,
                                                 @RequestParam(required = false, defaultValue = "all") String reservationBy
    ) {
        return travelReportService.getTravelReportByAccountClientProject(pageable, new String[]{name, surname, client, project, reservationBy});
    }

    @RequestMapping(value = "/nameSurnameClientProjectReservationBy/csv", produces = "text/csv")
    public void getFullReportCsv(Pageable pageable, HttpServletResponse response,
                                 @RequestParam(required = false, defaultValue = "all") String name,
                                 @RequestParam(required = false, defaultValue = "all") String surname,
                                 @RequestParam(required = false, defaultValue = "all") String client,
                                 @RequestParam(required = false, defaultValue = "all") String project,
                                 @RequestParam(required = false, defaultValue = "all") String reservationBy) throws IOException {
        List<TravelReportEntry> listToBeCsvMapped = travelReportService.getTravelReportByAccountClientProject(pageable, new String[]{name, surname, client, project, reservationBy});
        response.setCharacterEncoding("UTF-8");
        csvMapperService.mapToCsv(response.getWriter(), listToBeCsvMapped, TravelReportEntry.class, new String[]{"name", "surname", "client", "project", "reservationBy", "numberOfTravels", "numberOfNights"});
    }

    @GetMapping("/nameSurnameClientReservationBy")
    public List<TravelReportEntry> getTravelReportByAccountClient(Pageable pageable,

                                                                  @RequestParam(required = false, defaultValue = "all") String name,
                                                                  @RequestParam(required = false, defaultValue = "all") String surname,
                                                                  @RequestParam(required = false, defaultValue = "all") String client,
                                                                  @RequestParam(required = false, defaultValue = "all") String reservationBy) {
        return travelReportService.getTravelReportByAccountClient(pageable, new String[]{name, surname, client, reservationBy});
    }

    @RequestMapping(value = "/nameSurnameClientReservationBy/csv", produces = "text/csv")
    public void getTravelReportByAccountClientCsv(Pageable pageable, HttpServletResponse response,
                                                  @RequestParam(required = false, defaultValue = "all") String name,
                                                  @RequestParam(required = false, defaultValue = "all") String surname,
                                                  @RequestParam(required = false, defaultValue = "all") String client,
                                                  @RequestParam(required = false, defaultValue = "all") String reservationBy) throws IOException {
        List<TravelReportEntry> listToBeCsvMapped = travelReportService.getTravelReportByAccountClient(pageable, new String[]{name, surname, client, reservationBy});
        response.setCharacterEncoding("UTF-8");
        csvMapperService.mapToCsv(response.getWriter(), listToBeCsvMapped, TravelReportEntry.class, new String[]{"name", "surname", "client", "reservationBy", "numberOfTravels", "numberOfNights"});
    }

    @GetMapping("/nameSurnameReservationBy")
    public List<TravelReportEntry> getTravelReportByAccount(Pageable pageable,
                                                            @RequestParam(required = false, defaultValue = "all") String name,
                                                            @RequestParam(required = false, defaultValue = "all") String surname,
                                                            @RequestParam(required = false, defaultValue = "all") String reservationBy) {
        return travelReportService.getTravelReportByAccount(pageable, new String[]{name, surname, reservationBy});
    }

    @GetMapping(value = "/nameSurnameReservationBy/csv", produces = "text/csv")
    public void getTravelReportByAccountCsv(Pageable pageable, HttpServletResponse response,
                                            @RequestParam(required = false, defaultValue = "all") String name,
                                            @RequestParam(required = false, defaultValue = "all") String surname,
                                            @RequestParam(required = false, defaultValue = "all") String reservationBy) throws IOException {
        List<TravelReportEntry> listToBeCsvMapped = travelReportService.getTravelReportByAccount(pageable, new String[]{name, surname, reservationBy});
        response.setCharacterEncoding("UTF-8");
        csvMapperService.mapToCsv(response.getWriter(), listToBeCsvMapped, TravelReportEntry.class, new String[]{"name", "surname", "reservationBy", "numberOfTravels", "numberOfNights"});
    }

    @GetMapping("/clientProjectReservationBy")
    public List<TravelByClientProjectReportEntry> getTravelReportByClientProject(Pageable pageable,
                                                                                 @RequestParam(required = false, defaultValue = "all") String client,
                                                                                 @RequestParam(required = false, defaultValue = "all") String project,
                                                                                 @RequestParam(required = false, defaultValue = "all") String reservationBy
    ) {
        return travelReportService.getTravelReportByClientProject(pageable, new String[]{client, project, reservationBy});
    }

    @GetMapping(value = "/clientProjectReservationBy/csv", produces = "text/csv")
    public void getTravelReportByClientProjectCsv(Pageable pageable, HttpServletResponse response,
                                                  @RequestParam(required = false, defaultValue = "all") String client,
                                                  @RequestParam(required = false, defaultValue = "all") String project,
                                                  @RequestParam(required = false, defaultValue = "all") String reservationBy
    ) throws IOException {
        List<TravelByClientProjectReportEntry> listToBeCsvMapped = travelReportService.getTravelReportByClientProject(pageable, new String[]{client, project, reservationBy});
        response.setCharacterEncoding("UTF-8");
        csvMapperService.mapToCsv(response.getWriter(), listToBeCsvMapped, TravelByClientProjectReportEntry.class, new String[]{"client", "project", "reservationBy", "numberOfTravels", "numberOfNights"});
    }

    @GetMapping("/clientReservationBy")
    public List<TravelByClientProjectReportEntry> getTravelReportByClient(Pageable pageable,
                                                                          @RequestParam(required = false, defaultValue = "all") String client,
                                                                          @RequestParam(required = false, defaultValue = "all") String reservationBy
    ) {
        return travelReportService.getTravelReportByClient(pageable, new String[]{client, reservationBy});
    }

    @GetMapping(value = "/clientReservationBy/csv", produces = "text/csv")
    public void getTravelReportByClientCsv(Pageable pageable, HttpServletResponse response,
                                           @RequestParam(required = false, defaultValue = "all") String client,
                                           @RequestParam(required = false, defaultValue = "all") String reservationBy
    ) throws IOException {
        List<TravelByClientProjectReportEntry> listToBeCsvMapped = travelReportService.getTravelReportByClient(pageable, new String[]{client, reservationBy});
        response.setCharacterEncoding("UTF-8");
        csvMapperService.mapToCsv(response.getWriter(), listToBeCsvMapped, TravelByClientProjectReportEntry.class, new String[]{"client", "reservationBy", "numberOfTravels", "numberOfNights"});
    }
}

