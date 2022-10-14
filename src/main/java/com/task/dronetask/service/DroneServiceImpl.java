package com.task.dronetask.service;

import com.task.dronetask.entity.Drones;
import com.task.dronetask.exception.DroneNotFoundException;
import com.task.dronetask.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class DroneServiceImpl implements  DroneService{
    //autowire drone repo
    @Autowired
    DroneRepository droneRepository;

    //register drone
    @Override
    public Drones registerDrone(Drones drone) {
        return droneRepository.save(drone);
    }

    //find a specific drone
    @Override
    public Drones findDrone(Long id) {
        //the findByID method returns an optional which indicates you should handle null possibility
        Optional<Drones> drone = droneRepository.findById(id);
        //check f the drone is actually existing before unwrapping using get
        if(drone.isPresent()){
            return drone.get();
        }else{
            throw new DroneNotFoundException(id);
        }
    }

    //find all drones
    @Override
    public double  getDroneBattery(Long id) {
        //findbyid from crudRepo requires .get()  to return the entity
        return  findDrone(id).getBatteryCapacity();
    }

    //delete drone
    @Override
    public void deleteDrone(Long id) {
        droneRepository.deleteById(id);;
    }

    //get all drones
    @Override
    public List<Drones> getAllDrones() {
        //note that findAll returns an iterable which needs to be type casted to a list  you can return to the user
        return (List<Drones>) droneRepository.findAll();
    }
}
