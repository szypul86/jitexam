package com.jitexam.jitexam.service;

import com.jitexam.jitexam.dto.TravelByClientProjectReportEntry;
import com.jitexam.jitexam.dto.TravelReportEntry;
import com.jitexam.jitexam.repository.TravelReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelReportService {

    private final TravelReportRepository travelReportRepository;

    public List<TravelReportEntry> getTravelReportByAccountClientProject(Pageable pageable, String[] params) {
        return travelReportRepository.getTravelReportByAccountClientProject(pageable, params);
    }

    public List<TravelReportEntry> getTravelReportByAccountClient(Pageable pageable, String[] params) {
        return travelReportRepository.getTravelReportByAccountClient(pageable, params);
    }

    public List<TravelReportEntry> getTravelReportByAccount(Pageable pageable, String[] params) {
        return travelReportRepository.getTravelReportByAccount(pageable, params);
    }

    public List<TravelByClientProjectReportEntry> getTravelReportByClientProject(Pageable pageable, String[] params) {
        return travelReportRepository.getTravelReportByClientProject(pageable, params);
    }

    public List<TravelByClientProjectReportEntry> getTravelReportByClient(Pageable pageable, String[] params) {
        return travelReportRepository.getTravelReportByClient(pageable, params);
    }

}
