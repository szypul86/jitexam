package com.jitexam.jitexam.repository;

import com.jitexam.jitexam.dto.WorkTimeByClientAndProjectReportEntry;
import com.jitexam.jitexam.dto.WorkTimeFullReportEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class WorkTimeReportRepository {

    private final EntityManager entityManager;

    public List<WorkTimeFullReportEntry> getWorkTimeByAccountClientProjectReport(Pageable pageable) {
        String orderByQuery = createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery("select a.name as name ," +
                "a.surname as surname, " +
                "sum(d.worktime)/3600/1000 as hours, " +
                "c.name as client , " +
                "p.name as project " +
                "from account a " +
                "join timesheetreport t on a.id = t.accountid " +
                "join dailytime d on t.id = d.timesheetreportid " +
                "join client c on d.clientid = c.id " +
                "left join project p on d.projectid = p.id " +
                "group by  a.surname, a.name, c.name, p.name " +
                "having sum(d.worktime) is not null " +
                orderByQuery
        );
        List<Object[]> results = getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(WorkTimeFullReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    public List<WorkTimeFullReportEntry> getWorkTimeByAccountClientReport(Pageable pageable) {
        String orderByQuery = createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery("select a.name as name ," +
                "a.surname as surname, " +
                "sum(d.worktime)/3600/1000 as hours, " +
                "c.name as client , " +
                "from account a " +
                "join timesheetreport t on a.id = t.accountid " +
                "join dailytime d on t.id = d.timesheetreportid " +
                "join client c on d.clientid = c.id " +
                "group by  a.surname, a.name, c.name " +
                orderByQuery
        );
        List<Object[]> results = getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(WorkTimeFullReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    public List<WorkTimeFullReportEntry> getWorkTimeByAccountReport(Pageable pageable) {
        String orderByQuery = createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery("select a.name as name ," +
                "a.surname as surname, " +
                "sum(d.worktime)/3600/1000 as hours, " +
                "from account a " +
                "join timesheetreport t on a.id = t.accountid " +
                "join dailytime d on t.id = d.timesheetreportid " +
                "group by  a.surname, a.name " +
                orderByQuery
        );
        List<Object[]> results = getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(WorkTimeFullReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    public List<WorkTimeByClientAndProjectReportEntry> getWorkTimeByClientProjectReport(Pageable pageable) {
        String orderByQuery = createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery("select sum(d.worktime)/3600/1000 as hours, " +
                "c.name as client , " +
                "p.name as project " +
                "from  dailytime d " +
                "join client c on d.clientid = c.id " +
                "left join project p on d.projectid = p.id " +
                "group by c.name, p.name " +
                "having sum(d.worktime) is not null " +
                orderByQuery
        );
        List<Object[]> results = getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(WorkTimeByClientAndProjectReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    public List<WorkTimeByClientAndProjectReportEntry> getWorkTimeByClientReport(Pageable pageable) {
        String orderByQuery = createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery("select sum(d.worktime)/3600/1000 as hours, " +
                "c.name as client " +
                "from  dailytime d " +
                "join client c on d.clientid = c.id " +
                "group by c.name " +
                "having sum(d.worktime) is not null " +
                orderByQuery
        );
        List<Object[]> results = getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(WorkTimeByClientAndProjectReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    private String createOrderBySubquery(List<Sort.Order> sortList) {
        if (sortList.isEmpty()) {
            return "";
        }
        List<String> ordersByQuery = new ArrayList<>();
        sortList.forEach(o -> ordersByQuery.add(o.getProperty() + " " + o.getDirection().toString()));
        return "order by " + String.join(",", ordersByQuery);
    }
    private List<Object[]> getPaginatedResultsList(Pageable pageable, Query query) {
        return (List<Object[]>) query
                .setFirstResult(pageable.getPageSize() * pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }


}
