package com.task.dronetask.converter;

import com.task.dronetask.dto.DroneDto;
import com.task.dronetask.dto.RegDroneDto;
import com.task.dronetask.entity.Drones;

import java.util.List;

public interface DroneConverter {
    //convert Drone dto to drone Entity
    Drones droneDtoToEntity(DroneDto droneDto);
    //convert drone entity to Dto
    DroneDto droneEntityToDto(Drones droneEntity);
    //Entitty to regDrone Dto
    RegDroneDto droneEntityToRegDroneDto(Drones droneEntity);
    //convert drone list to dto
    List<DroneDto> droneListToDto(List<Drones> dronesList);
}
