package com.task.dronetask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuditBuilderImpl implements  AuditBuilder{

    private final Javers javers;
    @Override
    public String getEntitiesCreated(Class<?> classPassed, LocalDateTime startDate, LocalDateTime endDate) {
        //current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter javersFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

        String currentDate = LocalDateTime.now().format(javersFormat);
        String beginDate = startDate.format(javersFormat);
        String finishDate = endDate.format(javersFormat);
        log.info("start date is " + beginDate);
        log.info("end date is: " + finishDate);
        log.info("todays date is: "  + currentDate);
        //to utc
        LocalDateTime fromDate = LocalDateTime.parse(beginDate);
        LocalDateTime toDate= LocalDateTime.parse(finishDate);
        fromDate = fromDate.atZone(ZoneOffset.UTC).toLocalDateTime();
        toDate = toDate.atZone(ZoneOffset.UTC).toLocalDateTime();
        //build query
        QueryBuilder queryBuilder = QueryBuilder.byClass(classPassed)
                        .from(fromDate).to(toDate);
               List<CdoSnapshot> snapshots = javers.findSnapshots(queryBuilder.build());
        log.info("snapshots found" + snapshots);
        return javers.getJsonConverter().toJson(snapshots);
    }
}
