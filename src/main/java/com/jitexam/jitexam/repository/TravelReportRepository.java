package com.jitexam.jitexam.repository;

import com.jitexam.jitexam.dto.TravelByClientProjectReportEntry;
import com.jitexam.jitexam.dto.TravelReportEntry;
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
public class TravelReportRepository {

    private final EntityManager entityManager;
    private final QueryBuildingUtils queryBuildingUtils;


    public List<TravelReportEntry> getTravelReportByAccountClientProject(Pageable pageable, String[] params) {
        String orderByQuery = queryBuildingUtils.createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery(
                "select b.travelreservationmadeby as reservationby, " +
                        "count(b.id) as travelamount, " +
                        "sum(b.numberofnights) as nightsamount, " +
                        "a.name as name, " +
                        "a.surname as surname, " +
                        "c.name as client, " +
                        "COALESCE(p.name,'') as project " +
                        "from account a " +
                        "join businesstrip b on a.id = b.accountid " +
                        "join client c on b.clientid = c.id " +
                        "left join project p on b.projectid = p.id " +
                        "where a.name = case when ?1 = 'all' then a.name else ?1 end " +
                        "and a.surname = case when ?2 = 'all' then a.surname else ?2 end " +
                        "and c.name = case when ?3 = 'all' then c.name else ?3 end " +
                        "and COALESCE(p.name,'') = case when ?4 = 'all' then COALESCE(p.name,'') else ?4 end " +
                        "and b.travelreservationmadeby = case when ?5 = 'all' then b.travelreservationmadeby else ?5 end " +
                        "group by a.surname, a.name, c.name, p.name, b.travelreservationmadeby " +
                        orderByQuery
        );
        queryBuildingUtils.setQueryParams(query, params);
        List<Object[]> results = queryBuildingUtils.getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(TravelReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    public List<TravelReportEntry> getTravelReportByAccountClient(Pageable pageable, String[] params) {
        String orderByQuery = queryBuildingUtils.createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery(
                "select b.travelreservationmadeby as reservationby, " +
                        "count(b.id) as travelamount, " +
                        "a.name as name, " +
                        "a.surname as surname, " +
                        "c.name as client " +
                        "from account a " +
                        "join businesstrip b on a.id = b.accountid " +
                        "join client c on b.clientid = c.id " +
                        "where a.name = case when ?1 = 'all' then a.name else ?1 end " +
                        "and a.surname = case when ?2 = 'all' then a.surname else ?2 end " +
                        "and c.name = case when ?3 = 'all' then c.name else ?3 end " +
                        "and b.travelreservationmadeby = case when ?4 = 'all' then b.travelreservationmadeby else ?4 end " +
                        "group by a.surname, a.name, c.name, b.travelreservationmadeby " +
                        orderByQuery
        );
        queryBuildingUtils.setQueryParams(query, params);
        List<Object[]> results = queryBuildingUtils.getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(TravelReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    public List<TravelReportEntry> getTravelReportByAccount(Pageable pageable, String[] params) {
        String orderByQuery = queryBuildingUtils.createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery(
                "select b.travelreservationmadeby as reservationby, " +
                        "count(b.id) as travelamount, " +
                        "a.name as name, " +
                        "a.surname as surname " +
                        "from account a " +
                        "join businesstrip b on a.id = b.accountid " +
                        "where a.name = case when ?1 = 'all' then a.name else ?1 end " +
                        "and a.surname = case when ?2 = 'all' then a.surname else ?2 end " +
                        "and b.travelreservationmadeby = case when ?3 = 'all' then b.travelreservationmadeby else ?3 end " +
                        "group by a.surname, a.name, b.travelreservationmadeby " +
                        orderByQuery
        );
        queryBuildingUtils.setQueryParams(query, params);
        List<Object[]> results = queryBuildingUtils.getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(TravelReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    public List<TravelByClientProjectReportEntry> getTravelReportByClientProject(Pageable pageable, String[] params) {
        String orderByQuery = queryBuildingUtils.createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery(
                "select b.travelreservationmadeby as reservationby, " +
                        "count(b.id) as travelamount, " +
                        "sum(b.numberofnights) as nightsamount, " +
                        "c.name as client, " +
                        "COALESCE(p.name,'') as project " +
                        "from businesstrip b " +
                        "join client c on b.clientid = c.id " +
                        "left join project p on b.projectid = p.id " +
                        "where c.name = case when ?1 = 'all' then c.name else ?1 end " +
                        "and COALESCE(p.name,'') = case when ?2 = 'all' then COALESCE(p.name,'')ó else ?2 end " +
                        "and b.travelreservationmadeby = case when ?3 = 'all' then b.travelreservationmadeby else ?3 end " +
                        "group by c.name, p.name, b.travelreservationmadeby " +
                        orderByQuery
        );
        queryBuildingUtils.setQueryParams(query, params);
        List<Object[]> results = queryBuildingUtils.getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(TravelByClientProjectReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

    public List<TravelByClientProjectReportEntry> getTravelReportByClient(Pageable pageable, String[] params) {
        String orderByQuery = queryBuildingUtils.createOrderBySubquery(pageable.getSort().toList());
        Query query = entityManager.createNativeQuery(
                "select b.travelreservationmadeby as reservationby, " +
                        "count(b.id) as travelamount, " +
                        "sum(b.numberofnights) as nightsamount, " +
                        "c.name as client " +
                        "from businesstrip b " +
                        "join client c on b.clientid = c.id " +
                        "left join project p on b.projectid = p.id " +
                        "where c.name = case when ?1 = 'all' then c.name else ?1 end " +
                        "and b.travelreservationmadeby = case when ?2 = 'all' then b.travelreservationmadeby else ?2 end " +
                        "group by c.name, b.travelreservationmadeby " +
                        orderByQuery
        );
        queryBuildingUtils.setQueryParams(query, params);
        List<Object[]> results = queryBuildingUtils.getPaginatedResultsList(pageable, query);

        return results.stream()
                .map(TravelByClientProjectReportEntry::buildEntry)
                .collect(Collectors.toList());
    }

}

