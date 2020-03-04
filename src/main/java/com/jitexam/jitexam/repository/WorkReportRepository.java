package com.jitexam.jitexam.repository;

import com.jitexam.jitexam.dto.WorkReportEntry;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Repository
public class WorkReportRepository {
    private final EntityManager entityManager;

    public WorkReportRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Object[]> getAllWorkHoursReportData() {
        Query query = entityManager.createNativeQuery("select a.name, a.surname,  c.name as client, sum(d2.worktime) from account a join timesheetreport t on a.id = t.accountid join dailytime d2 on t.id = d2.timesheetreportid join client c on d2.clientid = c.id group by  a.surname, a.name, c.name"
        );
    return query.getResultList();
    }

    public List<Object[]> getAllWorkHoursReportDataSortedByParam(String param) {
        Query query = entityManager.createNativeQuery("select a.name as name , a.surname as surname,  c.name as client , sum(d2.worktime) as hours from account a join timesheetreport t on a.id = t.accountid join dailytime d2 on t.id = d2.timesheetreportid join client c on d2.clientid = c.id group by  a.surname, a.name, c.name " +
                " ORDER BY CASE WHEN :pamparam = 'name'  THEN a.name END ASC " +
                ", CASE WHEN :pamparam = 'surname' THEN a.surname END ASC"+
                ", CASE WHEN :pamparam = 'client' THEN c.name END ASC"+
                ", CASE WHEN :pamparam = 'hours' THEN sum(d2.worktime) END ASC"
        );
        query.setParameter("pamparam" , param);
        return query.getResultList();
    }

    public List<Object[]> getAllWorkHoursReportDataWithProjectSortedByParam(String param) {
        Query query = entityManager.createNativeQuery("select a.name as name , a.surname as surname,  c.name as client , p.name as project , sum(d2.worktime) as hours from account a join timesheetreport t on a.id = t.accountid join dailytime d2 on t.id = d2.timesheetreportid join client c on d2.clientid = c.id join project p on d2.projectid = p.id group by  a.surname, a.name, c.name " +
                " ORDER BY CASE WHEN :pamparam = 'name'  THEN a.name END ASC " +
                ", CASE WHEN :pamparam = 'surname' THEN a.surname END ASC"+
                ", CASE WHEN :pamparam = 'client' THEN c.name END ASC"+
                ", CASE WHEN :pamparam = 'hours' THEN sum(d2.worktime) END ASC" +
                ", CASE WHEN :pamparam = 'project' THEN p.name END ASC"
        );
        query.setParameter("pamparam" , param);
        return query.getResultList();
    }







}
