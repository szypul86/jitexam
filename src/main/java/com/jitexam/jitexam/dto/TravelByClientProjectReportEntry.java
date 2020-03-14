package com.jitexam.jitexam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelByClientProjectReportEntry {

    private String client;
    private String project;
    private String reservationBy;
    private BigInteger numberOfTravels;
    private BigInteger numberOfNights;

    public static TravelByClientProjectReportEntry buildEntry(Object[] results) {
        int size = results.length;
        return TravelByClientProjectReportEntry.builder()
                .reservationBy((String) results[0])
                .numberOfTravels((BigInteger) results[1])
                .numberOfNights((BigInteger) results[2])
                .client((String) results[3])
                .project((String) (size <= 4 ? null : results[4]))
                .build();
    }
}
