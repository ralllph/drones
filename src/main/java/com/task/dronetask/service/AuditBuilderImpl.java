package com.task.dronetask.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.dronetask.dto.AuditResponse;
import com.task.dronetask.exception.TypeNotRecognisedException;;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.core.metamodel.object.SnapshotType;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuditBuilderImpl implements  AuditBuilder{

    private final Javers javers;
    private final ObjectMapper mapper;
    @Override
    public List<Object> getChanges(Class<?> classPassed, LocalDate startDate, LocalDate endDate, String type) {
        // date formatting
        DateTimeFormatter javersFormatStart = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'00:00:00.000");
        DateTimeFormatter javersFormatEnd = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'23:59:59.59");
        String beginDate = startDate.format(javersFormatStart);
        String finishDate = endDate.format(javersFormatEnd);
        log.info("start date is " + beginDate);
        log.info("end date is: " + finishDate);
        //to utc
        LocalDateTime fromDate = LocalDateTime.parse(beginDate);
        LocalDateTime toDate= LocalDateTime.parse(finishDate);
        fromDate = fromDate.atZone(ZoneOffset.UTC).toLocalDateTime();
        toDate = toDate.atZone(ZoneOffset.UTC).toLocalDateTime();

        //string management
        String queryType = type.toLowerCase().trim();
        log.info("type before query" + queryType);
        //query

       if(queryType.equals("created")){
           String snapshots =  javersQueryBySnapshotType(classPassed,fromDate,toDate,SnapshotType.INITIAL);
            String snapshotsTrimmed  = snapshots.trim();
            log.info("snapshots found" + snapshotsTrimmed);
            String actionType = "create";
            List<Object> changesList = listChanges(snapshotsTrimmed, actionType,classPassed);
           return changesList;
       }

       else if(queryType.equals("updated")){
           String snapshots =  javersQueryBySnapshotType(classPassed,fromDate,toDate,SnapshotType.UPDATE);
           String snapshotsTrimmed  = snapshots.trim();
           log.info("snapshots found" + snapshotsTrimmed);
           String actionType = "update";
           List<Object> changesList = listChanges(snapshotsTrimmed, actionType,classPassed);
           return changesList;
       }


       else if(queryType.equals("deleted")){
           String snapshots = javersQueryBySnapshotType(classPassed,fromDate,toDate,SnapshotType.TERMINAL);
           String snapshotsTrimmed = snapshots.trim();
           log.info("snapshots found" + snapshotsTrimmed);
           String actionType = "delete";
           List<Object> changesList = listChanges(snapshotsTrimmed,actionType,classPassed);
           return changesList;
       }

       else {
           throw new TypeNotRecognisedException(type);
       }
    }

    public String javersQueryBySnapshotType(Class<?> classToQuery,LocalDateTime fromDate,LocalDateTime toDate, SnapshotType snapshotType){
        QueryBuilder queryBuilder = QueryBuilder.byClass(classToQuery)
                .from(fromDate).to(toDate).withSnapshotType(snapshotType);
        List<CdoSnapshot> snapshots = javers.findSnapshots(queryBuilder.build());
        log.info("snapshots found" + snapshots);
        return javers.getJsonConverter().toJson(snapshots);
    }

    public String javersQueryByVersion(Class<?> classToQuery, Long objectId, Long version){
        QueryBuilder queryBuilder = QueryBuilder.byInstanceId(objectId, classToQuery).
                withVersion(version-1);
         CdoSnapshot snapshot = javers.findSnapshots(queryBuilder.build()).
                stream().
                findFirst().
                orElse(null);

        log.info("snapshots found for previous version" + snapshot);
        return javers.getJsonConverter().toJson(snapshot);
    }

    public List<Object>  listChanges(String snapshot, String actionType,Class<?> classToQuery){
        if(!snapshot.equals("[]")){
            try {
                JsonNode jsonSnaps = mapper.readTree(snapshot);
                log.info("Json Snaps"+ jsonSnaps);
                List changes = new ArrayList<>();
                for(JsonNode snap: jsonSnaps){
                    AuditResponse auditResponse =new AuditResponse();
                    String author = snap.get("commitMetadata").get("author").asText();
                    log.info("author of commit" + author);
                    auditResponse.setOwner(author);
                    Object changedJson = snap.get("state");
                    Long entityId = snap.get("globalId").get("cdoId").asLong();
                    auditResponse.setEntityId(entityId);
                    log.info("Audit response individual:" + auditResponse);
                    Long version = snap.get("version").asLong();
                    if(actionType.equals("delete" ) || actionType.equals("update")){
                        String prevSnapshot =  javersQueryByVersion(classToQuery,entityId, version);
                        String prevSnapshotTrimmed = prevSnapshot.trim();
                        JsonNode jsonPrevState = mapper.readTree(prevSnapshotTrimmed);
                        log.info("jsonPrevState" + jsonPrevState);
                        Object prevState = jsonPrevState.get("state");
                        log.info("previous State"+prevState);
                        auditResponse.setPreviousJson(prevState);
                    }
                    auditResponse.setNewJson(changedJson);

                    auditResponse.setAction(actionType);
                    String datePerformed = snap.get("commitMetadata").get("commitDate").asText();
                    auditResponse.setTimeStamp(datePerformed);
                    changes.add(auditResponse);
                    log.info("Audit list :" +  changes);

                }
                return changes;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }else{
            log.info("No Records");
            return Collections.emptyList();
        }
    }
}

