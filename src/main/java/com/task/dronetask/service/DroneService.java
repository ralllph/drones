package com.task.dronetask.service;

import com.task.dronetask.dto.DroneDto;
import com.task.dronetask.dto.RegDroneDto;
import com.task.dronetask.dto.RegMedDto;


import java.util.List;

public interface DroneService {
    //register drone
    RegDroneDto registerDrone(DroneDto droneDto);
    //get drone battery level
    int getDroneBattery(Long id);
    //find drone by id
    DroneDto findDrone(Long id);
    //delete a drone
    void deleteDrone(Long id);
    //get all drones
    List<DroneDto> getAllDrones();
    //find medications assigned to a drone
    List<RegMedDto> findDroneMeds(Long droneId);
    //check available drones for loading
    List<DroneDto> availableDrones();

}
