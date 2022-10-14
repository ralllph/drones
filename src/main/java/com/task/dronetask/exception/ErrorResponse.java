package com.task.dronetask.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//this class helps create an object for our error response
public class ErrorResponse {
    //the error message should be a list  cuz they might default in filling so amny fields like serial,moddel...
    private List<String> message;
    private LocalDateTime timestamp;

    public ErrorResponse(List<String> message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
