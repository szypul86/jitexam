package com.jitexam.jitexam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkReportEntry {
    String name;
    String surname;
    String client;
    String project;
    BigDecimal sum;

    public static WorkReportEntry buildEntry(Object[] results) {
       int size = results.length;
        return WorkReportEntry.builder()
                .name((String) results[0])
                .surname((String) results[1])
                .sum((BigDecimal) results[2])
                .client((String) (size<=3? null: results[3]))
                .project((String) (size<=4? null: results[4]))
                .build();
    }
}
