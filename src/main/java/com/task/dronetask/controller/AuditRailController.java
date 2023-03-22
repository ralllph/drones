package com.task.dronetask.controller;

import com.task.dronetask.entity.User;
import com.task.dronetask.service.AuditBuilder;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@Slf4j
public class AuditRailController {


    private final AuditBuilder auditBuilder;

    @GetMapping("/dateRangeSnapshots")
    public ResponseEntity<String> getDateRangeSnapshots(
            @RequestParam("startDate") @DateTimeFormat(pattern =  "dd/MM/yyyy HH:mm:ss" ) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern =  "dd/MM/yyyy HH:mm:ss") LocalDateTime endDate
    ){
        log.info("start date:" + " "+ startDate);
        log.info("start date:" + " "+  endDate);
        return new ResponseEntity<>(auditBuilder.getEntitiesCreated(User.class,startDate,endDate), HttpStatus.OK);
    }

}
