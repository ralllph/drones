package com.task.dronetask.service;

import com.task.dronetask.converter.DroneConverterImpl;
import com.task.dronetask.converter.MedConverter;
import com.task.dronetask.dto.DroneDto;
import com.task.dronetask.dto.RegDroneDto;
import com.task.dronetask.dto.RegMedDto;
import com.task.dronetask.entity.Drones;
import com.task.dronetask.enums.State;
import com.task.dronetask.exception.DroneAlreadyExistsException;
import com.task.dronetask.exception.DroneNotFoundException;
import com.task.dronetask.exception.DroneUnableToLoadException;
import com.task.dronetask.repository.DroneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DroneServiceImpl implements  DroneService{
    //autowire drone repo
    //@Autowired
    DroneRepository droneRepository;
    DroneConverterImpl droneConverter;
    MedConverter medConverter;

    //register drone
    @Override
    public RegDroneDto registerDrone(DroneDto drone) {
        //convert the deserialized drone dto to an entity in order to save using service
        Drones droneEntity = droneConverter.droneDtoToEntity(drone);
       /* if(drone.getState().equals(State.LOADING)){
            if(droneEntity.getBatteryCapacity() < 25){
                throw new DroneUnableToLoadException();
            }
        }*/
        droneNameExists(droneEntity);
        //save entity using the save
        Drones savedDrone = droneRepository.save(droneEntity);
        //convert back to dto to mask data
        return droneConverter.droneEntityToRegDroneDto(savedDrone);
    }

    //find a specific drone
    @Override
    public DroneDto findDrone(Long id) {
        //the findByID method returns an optional which indicates you should handle null possibility
        Optional<Drones> drone = droneRepository.findById(id);
        //check f the drone is actually existing before unwrapping using get
        if(drone.isPresent()){
            return droneConverter.droneEntityToDto(drone.get());
        }else{
            throw new DroneNotFoundException(id);
        }
    }

    //find Meds attached to a drone
    @Override
    public List<RegMedDto> findDroneMeds(Long droneId) {
        DroneDto droneDto = findDrone(droneId);
        return medConverter.medListToDto(droneDto.getMedications());
    }

    //find all drones
    @Override
    public int  getDroneBattery(Long id) {
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
    public List<DroneDto> getAllDrones() {
        //note that findAll returns an iterable which needs to be type casted to a list  you can return to the user
        Iterable<Drones> allDrones = droneRepository.findAll();
        //convert Iterable to list
        List<Drones> droneEntityList= convertIterableToList(allDrones);
        return droneConverter.droneListToDto(droneEntityList);
    }

    @Override
    public List<DroneDto> availableDrones() {
        //list of drones without medications i.e unloaded
        List<DroneDto> availableDrones = new ArrayList<>();
        for (DroneDto drone: getAllDrones()){
            if(drone.getMedications().isEmpty()){
                availableDrones.add(drone);
            }
        }
        return availableDrones;
    }

    //convert iterable to list , find all returns iterable
    public List<Drones> convertIterableToList(Iterable<Drones> allDrones){
        //List to be returned
        List<Drones> dronesList = new ArrayList<>();
            for(Drones drone: allDrones){
                dronesList.add(drone);
            }
        return dronesList;
    }

    //check if drone name already exists
    public void droneNameExists(Drones drone){
        List<DroneDto> allDrones = getAllDrones();
        for (DroneDto eachDrone: allDrones){
            if(eachDrone.getSerialNumber().equals(drone.getSerialNumber())){
                throw new DroneAlreadyExistsException();
            }
        }
    }
}
