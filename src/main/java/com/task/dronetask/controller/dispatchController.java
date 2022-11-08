package com.task.dronetask.controller;

import com.task.dronetask.converter.DroneConverterImpl;
import com.task.dronetask.converter.MedConverterImpl;
import com.task.dronetask.dto.DroneDto;
import com.task.dronetask.dto.MedDto;
import com.task.dronetask.dto.RegDroneDto;
import com.task.dronetask.dto.RegMedDto;
import com.task.dronetask.entity.Drones;
import com.task.dronetask.entity.Medication;
import com.task.dronetask.service.DroneService;
import com.task.dronetask.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController

public class dispatchController {
    //wire drone service (done through allargs constructor
   // @Autowired
    DroneService droneService;
    //@Autowired
    MedicationService medicationService;
   // @Autowired
    DroneConverterImpl droneConverter;
    MedConverterImpl medConverter;

    //Intercept drone register
    @PostMapping("/regDrone")
    //we use the @Valid to perform your validations like notblank in the entity
    //its placed where the handler recieves the object from the sender cuz this is where json is deserialized to object
    //ehich are now placed using setters and getters
    public ResponseEntity<RegDroneDto> registerDrone(@RequestBody @Valid  DroneDto droneDto){
        return new ResponseEntity<>(droneService.registerDrone(droneDto), HttpStatus.CREATED);
    }

    //check drone battery level
    @GetMapping("/droneBattery/{id}")
    public ResponseEntity<Integer> findDrone(@PathVariable Long id){
        return new ResponseEntity<>(droneService.getDroneBattery(id), HttpStatus.OK);
    }

    //find drone
    //check drone battery level
    @GetMapping("/findDrone/{id}")
    public ResponseEntity<DroneDto> findADrone(@PathVariable Long id){
        return new ResponseEntity<>(droneService.findDrone(id), HttpStatus.OK);
    }

    //find medictaion assigned to drone
    @GetMapping("/findDroneMeds/{droneId}")
    public ResponseEntity<List<RegMedDto>> findDroneMeds(@PathVariable Long droneId){
        return new ResponseEntity<>(droneService.findDroneMeds(droneId), HttpStatus.OK);
    }

    //find all available drones for loading
    @GetMapping("/availableDrones")
    public ResponseEntity<List<DroneDto>> getAvailableDrones(){
        return new ResponseEntity<>(droneService.availableDrones(), HttpStatus.OK);
    }

    //intercept med registration
    @PostMapping("/regMed/{droneId}")
    //we use the @Valid to perform your validations like notblank in the entity
    //its placed where the handler recieves the object from the sender cuz this is where json is deserialized to object
    //ehich are now placed using setters and getters
    public ResponseEntity<RegMedDto> registerMed(@Valid  @RequestBody MedDto medDto, @PathVariable Long droneId){
        return new ResponseEntity<>(medicationService.saveMedication(medDto, droneId), HttpStatus.CREATED);
    }

    //delete a drone
    @DeleteMapping("/delDrone/{id}")
    public ResponseEntity<HttpStatus> delDrone(@PathVariable Long id){
        droneService.deleteDrone(id);
        //no content is 204 majorly used for delete
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //get all drones
    @GetMapping("/drones")
    public ResponseEntity<List<DroneDto>> getAllDrones(){
        return new ResponseEntity<>(droneService.getAllDrones(), HttpStatus.OK);
    }
}
