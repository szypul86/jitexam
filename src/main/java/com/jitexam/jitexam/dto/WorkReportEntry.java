package com.jitexam.jitexam.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WorkReportEntry {
    String name;
    String surname;
    String client;
    BigDecimal sum;

    public static WorkReportEntry buildEntry(Object[] results) {
        return WorkReportEntry.builder()
                .name((String) results[0])
                .surname((String) results[1])
                .client((String) results[2])
                .sum((BigDecimal) results[3])
                .build();
    }
}
