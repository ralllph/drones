package com.task.dronetask.converter;

import com.task.dronetask.dto.DroneDto;
import com.task.dronetask.dto.RegDroneDto;
import com.task.dronetask.entity.Drones;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// remember this allows you autowire
@Component
public class DroneConverterImpl  implements  DroneConverter{

    @Override
    public Drones droneDtoToEntity(DroneDto droneDto) {
        //set entity values using setters and getters from dto
        Drones droneEntity = new Drones();
        droneEntity.setId(droneDto.getId());
        droneEntity.setState(droneDto.getState());
        droneEntity.setModel(droneDto.getModel());
        droneEntity.setBatteryCapacity(droneDto.getBatteryCapacity());
        droneEntity.setSerialNumber(droneDto.getSerialNumber());
        droneEntity.setWeightLimit(droneDto.getWeightLimit());
        return droneEntity;
    }

    @Override
    public DroneDto droneEntityToDto(Drones droneEntity) {
        DroneDto droneDto = new DroneDto();
        droneDto.setId(droneEntity.getId());
        droneDto.setState(droneEntity.getState());
        droneDto.setModel(droneEntity.getModel());
        droneDto.setBatteryCapacity(droneEntity.getBatteryCapacity());
        droneDto.setSerialNumber(droneEntity.getSerialNumber());
        droneDto.setWeightLimit(droneEntity.getWeightLimit());
        droneDto.setMedications(droneEntity.getMedications());
        return  droneDto;
    }

    @Override
    public RegDroneDto droneEntityToRegDroneDto(Drones droneEntity) {
        RegDroneDto regDroneDto = new RegDroneDto();
        regDroneDto.setId(droneEntity.getId());
        regDroneDto.setState(droneEntity.getState());
        regDroneDto.setModel(droneEntity.getModel());
        regDroneDto.setBatteryCapacity(droneEntity.getBatteryCapacity());
        regDroneDto.setSerialNumber(droneEntity.getSerialNumber());
        regDroneDto.setWeightLimit(droneEntity.getWeightLimit());
        return regDroneDto;
    }

    @Override
    public List<DroneDto> droneListToDto(List<Drones> dronesList) {
        List<DroneDto> droneDtoList = new ArrayList<>();
        for (Drones drone: dronesList){
            DroneDto droneDto = droneEntityToDto(drone);
            droneDtoList.add(droneDto);
        }
        return droneDtoList;
    }
}
