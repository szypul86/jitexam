package com.jitexam.jitexam.service;

import com.jitexam.jitexam.dto.WorkTimeByClientAndProjectReportEntry;
import com.jitexam.jitexam.dto.WorkTimeFullReportEntry;
import com.jitexam.jitexam.repository.WorkTimeReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkTimeReportService {

    private final WorkTimeReportRepository workTimeReportRepository;

    public List<WorkTimeFullReportEntry> getWorkTimeByAccountClientProjectReport(Pageable pageable){
        return workTimeReportRepository.getWorkTimeByAccountClientProjectReport(pageable);
    }

    public List<WorkTimeFullReportEntry> getWorkTimeByAccountClientReport(Pageable pageable) {
        return workTimeReportRepository.getWorkTimeByAccountClientReport(pageable);
    }

    public List<WorkTimeFullReportEntry> getWorkTimeByAccountReport(Pageable pageable){
        return workTimeReportRepository.getWorkTimeByAccountReport(pageable);
    }

    public List<WorkTimeByClientAndProjectReportEntry> getWorkTimeClientProjectReport(Pageable pageable){
        return workTimeReportRepository.getWorkTimeByClientProjectReport(pageable);
    }

    public List<WorkTimeByClientAndProjectReportEntry> getWorkTimeByClientReport(Pageable pageable) {
        return workTimeReportRepository.getWorkTimeByClientReport(pageable);
    }






}
