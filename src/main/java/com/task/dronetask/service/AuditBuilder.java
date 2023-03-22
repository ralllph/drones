package com.task.dronetask.service;

import java.time.LocalDateTime;

public interface AuditBuilder {
    String getEntitiesCreated(Class<?> classPassed, LocalDateTime startDate, LocalDateTime endDate);
}
