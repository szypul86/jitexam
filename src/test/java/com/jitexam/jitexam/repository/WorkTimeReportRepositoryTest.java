package com.jitexam.jitexam.repository;

import com.jitexam.jitexam.dto.WorkTimeByAccountClientProjectReportEntry;
import com.jitexam.jitexam.dto.WorkTimeByClientProjectReportEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class WorkTimeReportRepositoryTest {

    @Autowired
    WorkTimeReportRepository workTimeReportRepository;

    @Test
    void getWorkTimeByAccountClientProjectReport() {
        //GIVEN
        String[] stringArray = {"Kamil", "Kiewisz", "any", "any"};
        //WHEN
        List<WorkTimeByAccountClientProjectReportEntry> result =
                workTimeReportRepository.getWorkTimeByAccountClientProjectReport(PageRequest.of(0, 50), stringArray);
        //THEN
        assertThat(result.stream().allMatch(r->r.getName().equals("Kamil")));
        assertThat(result.stream().allMatch(r->r.getSurname().equals("Kiewisz")));
    }


    @Test
    void getWorkTimeByClientProjectReport() {
        //GIVEN
        String[] stringArray = {"any", "any"};
        //WHEN
        List<WorkTimeByClientProjectReportEntry> result =
                workTimeReportRepository.getWorkTimeByClientProjectReport(PageRequest.of(0, 200), stringArray);
        //THEN
        assertThat(result.stream().allMatch(r->r.getHours()!=null));
        assertThat(result.stream().anyMatch(r->r.getProject().equals("")));
    }

}