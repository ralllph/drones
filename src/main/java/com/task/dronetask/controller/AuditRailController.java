package com.task.dronetask.controller;

import com.task.dronetask.entity.User;
import com.task.dronetask.service.AuditBuilder;
import lombok.AllArgsConstructor;;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class AuditRailController {


    private final AuditBuilder auditBuilder;

    @GetMapping("/dateRangeSnapshots/{type}")
    public ResponseEntity<List<Object>> getDateRangeSnapshots(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PathVariable String type
    ){
        log.info("start date:" + " "+ startDate);
        log.info("end date:" + " "+  endDate);
        log.info("type:" + " "+  type);
        return new ResponseEntity<>(auditBuilder.getChanges(User.class,startDate,endDate,type), HttpStatus.OK);
    }

}
