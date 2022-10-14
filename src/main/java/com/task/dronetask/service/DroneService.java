package com.task.dronetask.service;

import com.task.dronetask.entity.Drones;

import java.util.List;

public interface DroneService {
    //register drone
    Drones registerDrone(Drones drone);
    //get drone battery level
    double getDroneBattery(Long id);
    //find drone by id
    Drones findDrone(Long id);
    //delete a drone
    void deleteDrone(Long id);
    //get all drones
    List<Drones> getAllDrones();
}
