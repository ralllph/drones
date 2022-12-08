package com.task.dronetask.service;

import com.task.dronetask.dto.MedDto;
import com.task.dronetask.dto.RegMedDto;
import com.task.dronetask.entity.Medication;

public interface MedicationService {
    RegMedDto saveMedication(MedDto medDto, Long droneId);
    void deLeteMedById(Long medId);
}
