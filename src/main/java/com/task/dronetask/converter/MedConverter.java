package com.task.dronetask.converter;

import com.task.dronetask.dto.DroneDto;
import com.task.dronetask.dto.MedDto;
import com.task.dronetask.dto.RegMedDto;
import com.task.dronetask.entity.Drones;
import com.task.dronetask.entity.Medication;

import java.util.List;

public interface MedConverter {
    //convert Med dto to med Entity
    Medication medDtoToEntity(MedDto medDto);
    //convert drone entity to Dto
    MedDto medEntityToDto(Medication medEntity);
    //convert Entity to RegMedDto
    RegMedDto medEntityToRegDto(Medication medEntity);
    //convert drone list to dto
    List<RegMedDto> medListToDto(List<Medication> medsList);
}
