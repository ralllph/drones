package com.task.dronetask.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "medication")
//lombok dependency helps you remove boiler plate code
//@getters creates all your getters
@Data
//norags constructor creates an empty constructor
@NoArgsConstructor
//allargs constructor creates a parametized constructor
@AllArgsConstructor
public class Medication {
    @Id
    //the id must be generated automatically so use generated value annotaton
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "code")
    private String code;

    @Column(name = "image")
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
