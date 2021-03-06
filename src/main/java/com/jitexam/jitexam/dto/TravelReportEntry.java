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
    private String project;
    private String reservationBy;
    private BigInteger numberOfTravels;
    private BigInteger numberOfNights;

    public static TravelReportEntry buildEntry(Object[] results) {
        int size = results.length;
        return TravelReportEntry.builder()
                .reservationBy((String) results[0])
                .numberOfTravels((BigInteger) results[1])
                .numberOfNights((BigInteger) results[2])
                .name((String) results[3])
                .surname((String) results[4])
                .client((String) (size <= 5 ? null : results[5]))
                .project((String) (size <= 6 ? null : results[6]))
                .build();
    }
}
