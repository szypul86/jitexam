package com.jitexam.jitexam.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class TravelReport {
    private List<TravelReportEntry> entries;

    public static TravelReport buildReport(List<Object[]> results) {
        return new TravelReport(mapResultsToReportEntries(results));
    }

    private static List<TravelReportEntry> mapResultsToReportEntries(List<Object[]> results) {
        return results.stream()
                .map(TravelReportEntry::buildEntry)
                .collect(Collectors.toList());
    }
}
