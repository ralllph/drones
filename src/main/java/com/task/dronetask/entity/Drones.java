package com.task.dronetask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task.dronetask.enums.Model;
import com.task.dronetask.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

//@Entity tells spring boot we are going to create a table that stores drones entities
@Entity
//table helps you create a table
//use underscore  for satabase annotation names
@Table(name = "drones")
//lombok dependency helps you remove boiler plate code
//@getters creates all your getters
@Getter
@Setter
//norags constructor creates an empty constructor
@NoArgsConstructor
//allargs constructor creates a parametized constructor
@AllArgsConstructor
public class Drones {
    //@ column helps create a column in the table
    //remember to add the id for each entity, the id here is model
    @Id
    //the id must be generated automatically so use generated value annotaton
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "serial_number")
    @NotBlank(message = "serial number can't be blank")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "weight_limit" )
    private int weightLimit;

    @Column(name = "battery_capacity", nullable = false)
    private double batteryCapacity;

    @Enumerated(EnumType.STRING)
    private State state;
    //here remember the child table is the owner of the relationship cuz it holds the foreign key
    //so the non-owner uses mapped by which isthis drone entity to tell jpa dont create another table
    // 1 drone has a list of medications
    //mapped by property tells jpa that this relationship is mapped by the property drones asin the object
    //created in the many to one Drones drone in the medication entity.  so its mapped by drones
    @OneToMany(mappedBy = "drone")
    //we need at jso ignore to prevent this being serialized when it is sent back as json cuz it leads to an infinite loop
    //@JsonIgnore
    private List<Medication> medications;

}
