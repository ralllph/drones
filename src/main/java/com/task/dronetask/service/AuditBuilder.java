package com.task.dronetask.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AuditBuilder {
    List<Object> getChanges(Class<?> classPassed, LocalDate startDate, LocalDate endDate, String type);
}
