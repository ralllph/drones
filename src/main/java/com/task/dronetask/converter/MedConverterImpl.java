package com.task.dronetask.converter;

import com.task.dronetask.dto.DroneDto;
import com.task.dronetask.dto.MedDto;
import com.task.dronetask.dto.RegMedDto;
import com.task.dronetask.entity.Drones;
import com.task.dronetask.entity.Medication;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class MedConverterImpl implements  MedConverter{
    private final ModelMapper modelMapper;

    @Override
    public Medication medDtoToEntity(MedDto medDto) {
        return modelMapper.map(medDto, Medication.class);
    }

    @Override
    public MedDto medEntityToDto(Medication medEntity) {
        return modelMapper.map(medEntity,MedDto.class);
    }

    @Override
    public RegMedDto medEntityToRegDto(Medication medEntity) {
        return modelMapper.map(medEntity, RegMedDto.class);
    }

    @Override
    public List<RegMedDto> medListToDto(List<Medication> medsList) {
        List<RegMedDto> medDtoList = new ArrayList<>();
        for (Medication med: medsList){
            medDtoList.add(medEntityToRegDto(med));
        }
        return medDtoList;
    }
}
