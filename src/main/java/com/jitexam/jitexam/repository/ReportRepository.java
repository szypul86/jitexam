package com.jitexam.jitexam.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Repository
public class ReportRepository {
    private final EntityManager entityManager;

    public ReportRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Object[]> getAllWorkHoursReportData() {
        Query query = entityManager.createNativeQuery("select a.name, a.surname,  c.name as client, sum(d2.worktime)/3600/1000 from account a join timesheetreport t on a.id = t.accountid join dailytime d2 on t.id = d2.timesheetreportid join client c on d2.clientid = c.id group by  a.surname, a.name, c.name"
        );
    return query.getResultList();
    }

    public List<Object[]> getAllWorkHoursReportByAccountSortedByParam(String param) {
        Query query = entityManager.createNativeQuery("select a.name as name , a.surname as surname, sum(d2.worktime)/3600/1000 as hours from account a join timesheetreport t on a.id = t.accountid join dailytime d2 on t.id = d2.timesheetreportid join client c on d2.clientid = c.id group by  a.surname, a.name " +
                " ORDER BY CASE WHEN :pamparam = 'name'  THEN a.name END ASC " +
                ", CASE WHEN :param = 'surname' THEN a.surname END ASC"+
                ", CASE WHEN :param = 'hours' THEN sum(d2.worktime)/3600/1000 END ASC"
        );
        query.setParameter("param" , param);
        return query.getResultList();
    }

    public List<Object[]> getAllWorkHoursReportByClientSortedByParam(String param) {
        Query query = entityManager.createNativeQuery("select a.name as name , a.surname as surname, sum(d2.worktime)/3600/1000 as hours, c.name as client from account a join timesheetreport t on a.id = t.accountid join dailytime d2 on t.id = d2.timesheetreportid join client c on d2.clientid = c.id group by  a.surname, a.name, c.name " +
                " ORDER BY CASE WHEN :pamparam = 'name'  THEN a.name END ASC " +
                ", CASE WHEN :pamparam = 'surname' THEN a.surname END ASC"+
                ", CASE WHEN :pamparam = 'client' THEN c.name END ASC"+
                ", CASE WHEN :pamparam = 'hours' THEN sum(d2.worktime)/3600/1000 END ASC"
        );
        query.setParameter("pamparam" , param);
        return query.getResultList();
    }

    public List<Object[]> getAllWorkHoursReportDataByProjectSortedByParam(String param) {
        Query query = entityManager.createNativeQuery("select a.name as name , a.surname as surname, sum(d2.worktime)/3600/1000 as hours, c.name as client , p.name as project  from account a join timesheetreport t on a.id = t.accountid join dailytime d2 on t.id = d2.timesheetreportid join client c on d2.clientid = c.id left join project p on d2.projectid = p.id group by  a.surname, a.name, c.name, p.name " +
                " ORDER BY CASE WHEN :pamparam = 'name'  THEN a.name END ASC " +
                ", CASE WHEN :pamparam = 'surname' THEN a.surname END ASC"+
                ", CASE WHEN :pamparam = 'client' THEN c.name END ASC"+
                ", CASE WHEN :pamparam = 'hours' THEN sum(d2.worktime)/3600/1000 END ASC" +
                ", CASE WHEN :pamparam = 'project' THEN p.name END ASC"
        );
        query.setParameter("pamparam" , param);
        return query.getResultList();
    }

    public List<Object[]> getAllWorkHoursReportByProjectBySurname(String param, String surname) {
        Query query = entityManager.createNativeQuery("select a.name as name ," +
                " a.surname as surname," +
                " sum(d2.worktime)/3600/1000 as hours," +
                " c.name as client ," +
                " p.name as project" +
                "from account a " +
                "join timesheetreport t on a.id = t.accountid " +
                "join dailytime d2 on t.id = d2.timesheetreportid " +
                "join client c on d2.clientid = c.id " +
                "left join project p on d2.projectid = p.id " +
                "where a.surname = :surname " +
                "group by  a.surname, a.name, c.name, p.name " +
                " ORDER BY CASE WHEN :pamparam = 'name'  THEN a.name END ASC " +
                ", CASE WHEN :pamparam = 'surname' THEN a.surname END ASC" +
                ", CASE WHEN :pamparam = 'client' THEN c.name END ASC" +
                ", CASE WHEN :pamparam = 'hours' THEN sum(d2.worktime)/3600/1000 END ASC" +
                ", CASE WHEN :pamparam = 'project' THEN p.name END ASC"
        );
        query.setParameter("pamparam", param);
        query.setParameter("surname", surname);

        return query.getResultList();
    }

    public List<Object[]> getTravelReport() {
        Query query = entityManager.createNativeQuery(
                "select a.name as name , a.surname as surname, c.name as client, b.travelreservationmadeby, count(b.id) from account a join businesstrip b on a.id = b.accountid join client c on b.clientid = c.id group by a.surname, a.name, c.name, b.travelreservationmadeby"
        );
        return query.getResultList();
    }



}
