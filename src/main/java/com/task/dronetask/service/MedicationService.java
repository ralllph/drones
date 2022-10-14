package com.task.dronetask.service;

import com.task.dronetask.entity.Medication;

public interface MedicationService {
    Medication saveMedication(Medication med, Long droneId);
}
