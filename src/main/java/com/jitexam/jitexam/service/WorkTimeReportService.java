package com.jitexam.jitexam.service;

import com.jitexam.jitexam.dto.WorkTimeByAccountClientProjectReportEntry;
import com.jitexam.jitexam.dto.WorkTimeByClientProjectReportEntry;
import com.jitexam.jitexam.repository.WorkTimeReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class WorkTimeReportService {

    private final WorkTimeReportRepository workTimeReportRepository;

    public List<WorkTimeByAccountClientProjectReportEntry> getWorkTimeByAccountClientProjectReport(Pageable pageable, String[] params) {
        log.info("finding work time report by name: {}, surname: {}, client: {}, project: {}, page number: {}, page size: {}",
                params[0],params[1],params[2],params[3],pageable.getPageNumber(),pageable.getPageSize());
        List<WorkTimeByAccountClientProjectReportEntry> result = workTimeReportRepository.getWorkTimeByAccountClientProjectReport(pageable, params);
        log.info("getWorkTimeByAccountClientProjectReport success, found {} elements",result.size());
        return result;

    }

    public List<WorkTimeByAccountClientProjectReportEntry> getWorkTimeByAccountClientReport(Pageable pageable, String[] params) {
        return workTimeReportRepository.getWorkTimeByAccountClientReport(pageable, params);
    }

    public List<WorkTimeByAccountClientProjectReportEntry> getWorkTimeByAccountReport(Pageable pageable, String[] params) {
        return workTimeReportRepository.getWorkTimeByAccountReport(pageable, params);
    }

    public List<WorkTimeByClientProjectReportEntry> getWorkTimeClientProjectReport(Pageable pageable, String[] params) {
        return workTimeReportRepository.getWorkTimeByClientProjectReport(pageable, params);
    }

    public List<WorkTimeByClientProjectReportEntry> getWorkTimeByClientReport(Pageable pageable, String[] params) {
        return workTimeReportRepository.getWorkTimeByClientReport(pageable, params);
    }


}
