package com.task.dronetask.dto;

import com.task.dronetask.entity.Medication;
import com.task.dronetask.enums.Model;
import com.task.dronetask.enums.State;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
//This drone dto is just a replica of the entity class we plan to use instead of entity directly to hide data
//There is a converter that converts it back to entity and vice versa using getters and setters
public class DroneDto {

    private Long id;

    @Size(min = 1, max = 100, message = "serial number must have between 1-100 characters")
    private String serialNumber;

    private Model model;

    @NotNull(message = "please provide valid weight ")
    @DecimalMin(value = "0.00", message = "invalid weight limit ", inclusive = true)
    @DecimalMax(value = "500.00", message= "weight limit cannot exceed 500gr", inclusive = true)
    private BigDecimal weightLimit;

    @NotNull(message = "Enter a valid battery capacity")
    private int batteryCapacity;

    private State state;

    private List<Medication> medications;
}
