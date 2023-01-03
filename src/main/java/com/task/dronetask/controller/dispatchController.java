package com.task.dronetask.controller;
import com.task.dronetask.converter.DroneConverter;
import com.task.dronetask.converter.MedConverter;
import com.task.dronetask.dto.DroneDto;
import com.task.dronetask.dto.MedDto;
import com.task.dronetask.dto.RegDroneDto;
import com.task.dronetask.dto.RegMedDto;
import com.task.dronetask.service.DroneService;
import com.task.dronetask.service.MedicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController

@Tag(name = "Dispatch Controller", description = "Dispatch controller to control drone activities")
public class dispatchController {
    //wire drone service (done through allargs constructor
   // @Autowired
    DroneService droneService;
    //@Autowired
    MedicationService medicationService;
   // @Autowired
    DroneConverter droneConverter;
    MedConverter medConverter;

    //Intercept drone register
    @PostMapping("/regDrone")
    //@Operation is used by open api to describe the  mapping for openApi
    @Operation(summary = "Register drone", description = "Register a drone")
    //we use the @Valid to perform your validations like notblank in the entity
    //its placed where the handler recieves the object from the sender cuz this is where json is deserialized to object
    //ehich are now placed using setters and getters
    public ResponseEntity<RegDroneDto> registerDrone(@RequestBody @Valid  DroneDto droneDto){
        return new ResponseEntity<>(droneService.registerDrone(droneDto), HttpStatus.CREATED);
    }

    //check drone battery level
    @GetMapping("/droneBattery/{id}")
    @Operation(summary = "Drone Battery Level", description = "Check the battery level of a drone")
    public ResponseEntity<Integer> findDrone(@PathVariable Long id ){
        return new ResponseEntity<>(droneService.getDroneBattery(id), HttpStatus.OK);
    }

    //find drone
    //check drone battery level
    @GetMapping("/findDrone/{id}")
    @Operation(summary = "Find Drone" , description = "Find a drone by id")
    public ResponseEntity<DroneDto> findADrone(@PathVariable Long id){
        return new ResponseEntity<>(droneService.findDrone(id), HttpStatus.OK);
    }

    //find medictaion assigned to drone
    @GetMapping("/findDroneMeds/{droneId}")
    @Operation(summary = "Find Drone Meds", description = "Find medications assigned to a drone")
    public ResponseEntity<List<RegMedDto>> findDroneMeds(@PathVariable Long droneId){
        return new ResponseEntity<>(droneService.findDroneMeds(droneId), HttpStatus.OK);
    }

    //find all available drones for loading
    @GetMapping("/availableDrones")
    @Operation(summary = "Available drones", description = "Check drones available for loading medications(drones can't be overloaded)")
    public ResponseEntity<List<DroneDto>> getAvailableDrones(){
        return new ResponseEntity<>(droneService.availableDrones(), HttpStatus.OK);
    }

    //intercept med registration
    @PostMapping("/regMed/{droneId}")
    @Operation(summary = "Register med", description = "Assign a medication to a drone by Id")
    //we use the @Valid to perform your validations like notblank in the entity
    //its placed where the handler recieves the object from the sender cuz this is where json is deserialized to object
    //ehich are now placed using setters and getters
    public ResponseEntity<RegMedDto> registerMed(@Valid  @RequestBody MedDto medDto, @PathVariable Long droneId){
        return new ResponseEntity<>(medicationService.saveMedication(medDto, droneId), HttpStatus.CREATED);
    }

    //delete a drone
    @DeleteMapping("/delDrone/{id}")
    @Operation(summary = "Delete drone", description = "Delete a drone by Id")
    public ResponseEntity<HttpStatus> delDrone(@PathVariable Long id){
        droneService.deleteDrone(id);
        //no content is 204 majorly used for delete
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //get all drones
    @GetMapping("/drones")
    @Operation(summary = "All Drones", description = "Check all drones(with and without medications)")
    public ResponseEntity<List<DroneDto>> getAllDrones(){
        return new ResponseEntity<>(droneService.getAllDrones(), HttpStatus.OK);
    }
}
