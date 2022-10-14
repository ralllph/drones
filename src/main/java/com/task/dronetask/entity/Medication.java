package com.task.dronetask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "medication")
//lombok dependency helps you remove boiler plate code
//@getters creates all your getters
@Getter
@Setter
//norags constructor creates an empty constructor
@NoArgsConstructor
//allargs constructor creates a parametized constructor
@AllArgsConstructor
public class Medication {
    @Id
    //the id must be generated automatically so use generated value annotaton
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "name can't be blank")
    private String name;

    @Column(name = "weight")
    @NotBlank(message = "weight can't be blank")
    private String weight;

    @Column(name =  "code")
    @NotBlank(message = "code can't be blank")
    private String code;

    @Column(name = "image")
    @NotBlank(message = "image can't be blank")
    private String image;

    //here many medicationes can be assigned to one drone
    @ManyToOne
    //here the relationship is defined by a foreign key from the drones entity
    //child table always manages the foreign key
    //define a foreign key column that joins two tables using @join column
    //referenced column name is the name of the name of the column of the primary key which is the id of drone
    //we named the drone id column id so referencedcolumn here should be named id
    @JoinColumn(name = "drone_id", referencedColumnName = "id")
    private Drones drone;

}
