package com.jitexam.jitexam.repository;

import com.jitexam.jitexam.dto.WorkTimeByAccountClientProjectReportEntry;
import com.jitexam.jitexam.dto.WorkTimeByClientProjectReportEntry;
import com.jitexam.jitexam.utils.QueryBuildingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class WorkTimeReportRepository {

    private final EntityManager entityManager;
    private final QueryBuildingUtils queryBuildingUtils;

    public List<WorkTimeByAccountClientProjectReportEntry> getWorkTimeByAccountClientProjectReport(Pageable pageable, String[] params) {
        String orderByQuery = queryBuildingUtils.createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery(
                "select a.name as name ," +
                        "a.surname as surname, " +
                        "sum(d.worktime)/3600/1000 as hours, " +
                        "c.name as client , " +
                        "COALESCE(p.name,'') as project " +
                        "from account a " +
                        "join timesheetreport t on a.id = t.accountid " +
                        "join dailytime d on t.id = d.timesheetreportid " +
                        "join client c on d.clientid = c.id " +
                        "left join project p on d.projectid = p.id " +
                        "where a.name = case when ?1 = 'all' then a.name else ?1 end " +
                        "and a.surname = case when ?2 = 'all' then a.surname else ?2 end " +
                        "and c.name = case when ?3 = 'all' then c.name else ?3 end " +
                        "and COALESCE(p.name,'') = case when ?4 = 'all' then COALESCE(p.name,'') else ?4 end " +
                        "group by  a.surname, a.name, c.name, p.name " +
                        "having sum(d.worktime) is not null " +
                        orderByQuery
        );
        queryBuildingUtils.setQueryParams(query, params);
        List<Object[]> results = queryBuildingUtils.getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(WorkTimeByAccountClientProjectReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    public List<WorkTimeByAccountClientProjectReportEntry> getWorkTimeByAccountClientReport(Pageable pageable, String[] params) {
        String orderByQuery = queryBuildingUtils.createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery(
                "select a.name as name ," +
                        "a.surname as surname, " +
                        "sum(d.worktime)/3600/1000 as hours, " +
                        "c.name as client " +
                        "from account a " +
                        "join timesheetreport t on a.id = t.accountid " +
                        "join dailytime d on t.id = d.timesheetreportid " +
                        "join client c on d.clientid = c.id " +
                        "where a.name = case when ?1 = 'all' then a.name else ?1 end " +
                        "and a.surname = case when ?2 = 'all' then a.surname else ?2 end " +
                        "and c.name = case when ?3 = 'all' then c.name else ?3 end " +
                        "group by  a.surname, a.name, c.name " +
                        orderByQuery
        );
        queryBuildingUtils.setQueryParams(query, params);
        List<Object[]> results = queryBuildingUtils.getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(WorkTimeByAccountClientProjectReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    public List<WorkTimeByAccountClientProjectReportEntry> getWorkTimeByAccountReport(Pageable pageable, String[] params) {
        String orderByQuery = queryBuildingUtils.createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery(
                "select a.name as name ," +
                        "a.surname as surname, " +
                        "sum(d.worktime)/3600/1000 as hours " +
                        "from account a " +
                        "join timesheetreport t on a.id = t.accountid " +
                        "join dailytime d on t.id = d.timesheetreportid " +
                        "where a.name = case when ?1 = 'all' then a.name else ?1 end " +
                        "and a.surname = case when ?2 = 'all' then a.surname else ?2 end " +
                        "group by  a.surname, a.name " +
                        orderByQuery
        );
        queryBuildingUtils.setQueryParams(query, params);
        List<Object[]> results = queryBuildingUtils.getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(WorkTimeByAccountClientProjectReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    public List<WorkTimeByClientProjectReportEntry> getWorkTimeByClientProjectReport(Pageable pageable, String[] params) {
        String orderByQuery = queryBuildingUtils.createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery(
                "select sum(d.worktime)/3600/1000 as hours, " +
                        "c.name as client , " +
                        "COALESCE(p.name,'') as project " +
                        "from  dailytime d " +
                        "join client c on d.clientid = c.id " +
                        "left join project p on d.projectid = p.id " +
                        "where c.name = case when ?1 = 'all' then c.name else ?1 end " +
                        "and COALESCE(p.name , '') = case when ?2 = 'all' then COALESCE(p.name , '') else ?2 end " +
                        "group by c.name, p.name " +
                        "having sum(d.worktime) is not null " +
                        orderByQuery
        );
        queryBuildingUtils.setQueryParams(query, params);
        List<Object[]> results = queryBuildingUtils.getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(WorkTimeByClientProjectReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    public List<WorkTimeByClientProjectReportEntry> getWorkTimeByClientReport(Pageable pageable, String[] params) {
        String orderByQuery = queryBuildingUtils.createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery(
                "select sum(d.worktime)/3600/1000 as hours, " +
                        "c.name as client " +
                        "from  dailytime d " +
                        "join client c on d.clientid = c.id " +
                        "where c.name = case when ?1 = 'all' then c.name else ?1 end " +
                        "group by c.name " +
                        "having sum(d.worktime) is not null " +
                        orderByQuery
        );
        queryBuildingUtils.setQueryParams(query, params);
        List<Object[]> results = queryBuildingUtils.getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(WorkTimeByClientProjectReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

}
