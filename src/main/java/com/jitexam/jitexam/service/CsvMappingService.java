package com.jitexam.jitexam.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;

@Slf4j
@Service
public class CsvMappingService {

    public <T> void mapToCsv(PrintWriter writer, List<T> objectsList, Class<T> outputClass, String[] columns) {

        try {
            ColumnPositionMappingStrategy<T> mapStrategy
                    = new ColumnPositionMappingStrategy<>();
            mapStrategy.setType(outputClass);
            mapStrategy.setColumnMapping(columns);
            StatefulBeanToCsv<T> btcsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build();
            btcsv.write(objectsList);
            log.info("Report of {} successfully mapped to csv", outputClass.getName().substring(outputClass.getName().lastIndexOf('.') + 1));

        } catch (CsvException ex) {
            log.error("Error mapping Bean to CSV", ex);
        }
    }
}