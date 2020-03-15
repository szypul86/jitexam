package com.jitexam.jitexam.service;

import com.jitexam.jitexam.dto.TravelByClientProjectReportEntry;
import com.jitexam.jitexam.dto.TravelReportEntry;
import com.jitexam.jitexam.repository.TravelReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelReportService {

    private final TravelReportRepository travelReportRepository;

    public List<TravelReportEntry> getTravelReportByAccountClientProject(Pageable pageable, String[] params) {
        log.info("Finding travel report by name: {}, surname: {}, client: {}, project: {}, reservationBy: {} page number: {}, page size: {}",
                params[0], params[1], params[2], params[3] ,params[4], pageable.getPageNumber(), pageable.getPageSize());
        List<TravelReportEntry> result = travelReportRepository.getTravelReportByAccountClientProject(pageable, params);
        log.info("getTravelReportByAccountClientProject success, found {} elements", result.size());
        return result;
    }

    public List<TravelReportEntry> getTravelReportByAccountClient(Pageable pageable, String[] params) {
        log.info("Finding travel report by name: {}, surname: {}, client: {}, reservationBy: {} page number: {}, page size: {}",
                params[0], params[1], params[2], params[3], pageable.getPageNumber(), pageable.getPageSize());
        List<TravelReportEntry> result = travelReportRepository.getTravelReportByAccountClient(pageable, params);
        log.info("getTravelReportByAccountClient success, found {} elements", result.size());
        return result;
    }

    public List<TravelReportEntry> getTravelReportByAccount(Pageable pageable, String[] params) {
        log.info("Finding travel report by name: {}, surname: {}, reservationBy: {} page number: {}, page size: {}",
                params[0], params[1], params[2], pageable.getPageNumber(), pageable.getPageSize());
        List<TravelReportEntry> result = travelReportRepository.getTravelReportByAccount(pageable, params);
        log.info("getTravelReportByAccount success, found {} elements", result.size());
        return result;
    }

    public List<TravelByClientProjectReportEntry> getTravelReportByClientProject(Pageable pageable, String[] params) {
        log.info("Finding travel report by client: {}, project: {}, reservationBy: {} page number: {}, page size: {}",
                params[0], params[1], params[2], pageable.getPageNumber(), pageable.getPageSize());
        List<TravelByClientProjectReportEntry> result = travelReportRepository.getTravelReportByClientProject(pageable, params);
        log.info("getTravelReportByAccount success, found {} elements", result.size());
        return result;
    }

    public List<TravelByClientProjectReportEntry> getTravelReportByClient(Pageable pageable, String[] params) {
        log.info("Finding travel report by client: {}, project: {}, reservationBy: {} page number: {}, page size: {}",
                params[0], params[1], pageable.getPageNumber(), pageable.getPageSize());
        List<TravelByClientProjectReportEntry> result = travelReportRepository.getTravelReportByClient(pageable, params);
        log.info("getTravelReportByAccount success, found {} elements", result.size());
        return result;
    }

}
