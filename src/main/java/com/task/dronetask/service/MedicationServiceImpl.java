package com.task.dronetask.service;

import com.task.dronetask.converter.MedConverterImpl;
import com.task.dronetask.dto.MedDto;
import com.task.dronetask.dto.RegDroneDto;
import com.task.dronetask.dto.RegMedDto;
import com.task.dronetask.entity.Drones;
import com.task.dronetask.entity.Medication;
import com.task.dronetask.exception.DroneNotFoundException;
import com.task.dronetask.exception.DroneWeightExceededException;
import com.task.dronetask.repository.DroneRepository;
import com.task.dronetask.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MedicationServiceImpl implements MedicationService{
   // @Autowired
    MedicationRepository medicationRepository;
   // @Autowired
    DroneRepository droneRepository;
    MedConverterImpl medConverter;

    @Override
    public RegMedDto saveMedication(MedDto medDto, Long droneId) {
        //convert med dto to med
        Medication med = medConverter.medDtoToEntity(medDto);
        Optional<Drones> drone = droneRepository.findById(droneId);
        if(drone.isPresent()){
            //prevent medication from being heavier than drone
            //compare to method e.g c.compareto(y) (must both be big decimals) returns 0 if x equals y, (-)1 if xis smaller than y. returns 1 if y is greater than x
            List<Medication> droneMeds = drone.get().getMedications();
            BigDecimal totDronePrevMeds, newWeight,droneWeight;
            //calculate the total weight of meds drone was carrying before
            totDronePrevMeds =calculateTotalMedWeight(droneMeds);
            // the new medication weight
            newWeight = med.getWeight();
            //the drones actual weight
            droneWeight = drone.get().getWeightLimit();
            if(totDronePrevMeds.add(newWeight).compareTo(droneWeight) <=0){
                med.setDrone(drone.get());
                return medConverter.medEntityToRegDto(medicationRepository.save(med));
            }
            else{
                throw new DroneWeightExceededException(droneId);
            }
        }else {
            throw new DroneNotFoundException(droneId);
        }
    }

    public BigDecimal calculateTotalMedWeight(List<Medication> droneMeds){
        // we are calculating the total weight for each med a drone has
        BigDecimal totalDroneMeds = BigDecimal.ZERO;
        for(Medication med: droneMeds){
            //add to total
            totalDroneMeds=totalDroneMeds.add(med.getWeight());
        }
        return totalDroneMeds;
    }
}
