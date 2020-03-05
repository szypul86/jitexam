package com.jitexam.jitexam.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class WorkReport {

    private List<WorkReportEntry> entries;

    public WorkReport(List<WorkReportEntry> entries) {
        this.entries = entries;
    }

    public static WorkReport buildReport(List<Object[]> results) {
        return new WorkReport(mapResultsToReportEntries(results));
    }

    private static List<WorkReportEntry> mapResultsToReportEntries(List<Object[]> results) {
        return results.stream()
                .map(WorkReportEntry::buildEntry)
                .collect(Collectors.toList());
    }
}