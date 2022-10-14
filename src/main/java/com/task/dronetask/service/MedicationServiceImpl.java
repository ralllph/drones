package com.task.dronetask.service;

import com.task.dronetask.entity.Drones;
import com.task.dronetask.entity.Medication;
import com.task.dronetask.exception.DroneNotFoundException;
import com.task.dronetask.repository.DroneRepository;
import com.task.dronetask.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicationServiceImpl implements MedicationService{
    @Autowired
    MedicationRepository medicationRepository;
    @Autowired
    DroneRepository droneRepository;

    @Override
    public Medication saveMedication(Medication med,Long droneId) {
        Optional<Drones> drone = droneRepository.findById(droneId);
        if(drone.isPresent()){
            med.setDrone(drone.get());
            return medicationRepository.save(med);
        }else {
            throw new DroneNotFoundException(droneId);
        }
    }
}
