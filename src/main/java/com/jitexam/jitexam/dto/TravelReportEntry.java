package com.jitexam.jitexam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelReportEntry {
    private String name;
    private String surname;
    private String client;
    private String reservationBy;
    private BigInteger sum;

    public static TravelReportEntry buildEntry(Object[] results) {
        return TravelReportEntry.builder()
                .name((String) results[0])
                .surname((String) results[1])
                .client((String) results[2])
                .reservationBy((String) results[3])
                .sum((BigInteger) results[4])
                .build();
    }
}
