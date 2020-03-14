package com.jitexam.jitexam.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class QueryBuildingUtils {

    public String createOrderBySubquery(List<Sort.Order> sortList) {
        if (sortList.isEmpty()) {
            return "";
        }
        List<String> ordersByQuery = new ArrayList<>();
        sortList.forEach(o -> ordersByQuery.add(o.getProperty().replace(";", "") + " " + o.getDirection().toString()));
        return "order by " + String.join(",", ordersByQuery);
    }

    public List<Object[]> getPaginatedResultsList(Pageable pageable, Query query) {

        return (List<Object[]>) query
                .setFirstResult(pageable.getPageSize() * pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    public void setQueryParams(Query query, String[] params) {
        int n = 1;
        for (String param : params) {
            query.setParameter(n, param);
            n++;
        }
    }
}
