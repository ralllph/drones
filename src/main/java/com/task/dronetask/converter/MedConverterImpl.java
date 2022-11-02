package com.task.dronetask.converter;

import com.task.dronetask.dto.DroneDto;
import com.task.dronetask.dto.MedDto;
import com.task.dronetask.dto.RegMedDto;
import com.task.dronetask.entity.Drones;
import com.task.dronetask.entity.Medication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MedConverterImpl implements  MedConverter{

    @Override
    public Medication medDtoToEntity(MedDto medDto) {
        Medication medEntity = new Medication();
        medEntity.setId(medDto.getId());
        medEntity.setName(medDto.getName());
        medEntity.setWeight(medDto.getWeight());
        medEntity.setCode(medDto.getCode());
        medEntity.setImage(medDto.getImage());
        medEntity.setDrone(medDto.getDrone());
        return medEntity;
    }

    @Override
    public MedDto medEntityToDto(Medication medEntity) {
        MedDto medDto = new MedDto();
        medDto.setId(medEntity.getId());
        medDto.setName(medEntity.getName());
        medDto.setWeight(medEntity.getWeight());
        medDto.setCode(medEntity.getCode());
        medDto.setImage(medEntity.getImage());
        medDto.setDrone(medEntity.getDrone());
        return  medDto;
    }

    @Override
    public RegMedDto medEntityToRegDto(Medication medEntity) {
        RegMedDto medDto = new RegMedDto();
        medDto.setId(medEntity.getId());
        medDto.setName(medEntity.getName());
        medDto.setWeight(medEntity.getWeight());
        medDto.setCode(medEntity.getCode());
        medDto.setImage(medEntity.getImage());
        return  medDto;
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
