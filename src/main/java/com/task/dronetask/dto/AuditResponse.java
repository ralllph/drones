package com.task.dronetask.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuditResponse {

    private String owner;
    private Long entityId;
    private Object previousJson;
    private Object newJson;
    private String action;
    private String timeStamp;


}
