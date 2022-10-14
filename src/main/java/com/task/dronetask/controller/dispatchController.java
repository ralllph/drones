package com.task.dronetask.controller;

import com.task.dronetask.entity.Drones;
import com.task.dronetask.entity.Medication;
import com.task.dronetask.service.DroneService;
import com.task.dronetask.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class dispatchController {
    //wire drone service
    @Autowired
    DroneService droneService;
    @Autowired
    MedicationService medicationService;

    //Intercept drone register
    @PostMapping("/regDrone")
    //we use the @Valid to perform your validations like notblank in the entity
    //its placed where the handler recieves the object from the sender cuz this is where json is deserialized to object
    //ehich are now placed using setters and getters
    public ResponseEntity<Drones> registerDrone(@Valid  @RequestBody Drones drone){
        return new ResponseEntity<>(droneService.registerDrone(drone), HttpStatus.CREATED);
    }


    //check drone battery level
    @GetMapping("/droneBattery/{id}")
    public ResponseEntity<Double> findDrone(@PathVariable Long id){
        return new ResponseEntity<>(droneService.getDroneBattery(id), HttpStatus.OK);
    }

    //find drone
    //check drone battery level
    @GetMapping("/findDrone/{id}")
    public ResponseEntity<Drones> findADrone(@PathVariable Long id){
        return new ResponseEntity<>(droneService.findDrone(id), HttpStatus.OK);
    }

    //intercept med registration
    @PostMapping("/regMed/{droneId}")
    //we use the @Valid to perform your validations like notblank in the entity
    //its placed where the handler recieves the object from the sender cuz this is where json is deserialized to object
    //ehich are now placed using setters and getters
    public ResponseEntity<Medication> registerMed(@Valid  @RequestBody Medication med, @PathVariable Long droneId){
        return new ResponseEntity<>(medicationService.saveMedication(med,droneId), HttpStatus.CREATED);
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
    public ResponseEntity<List<Drones>> getAllDrones(){
        return new ResponseEntity<>(droneService.getAllDrones(), HttpStatus.OK);
    }
}
