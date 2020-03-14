package com.jitexam.jitexam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkTimeByClientAndProjectReportEntry {
    String client;
    String project;
    BigDecimal sum;

    public static WorkTimeByClientAndProjectReportEntry buildEntry(Object[] results) {
        int size = results.length;
        return WorkTimeByClientAndProjectReportEntry.builder()
                .client((String) results[1])
                .sum(((BigDecimal) results[0]).setScale(2, RoundingMode.CEILING))
                .project((String) (size<=2? null: results[2]))
                .build();
    }
}
