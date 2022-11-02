package com.task.dronetask.dto;

import com.task.dronetask.enums.Model;
import com.task.dronetask.enums.State;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
// this dto is just to prevent us from returning medications when returning to the user
public class RegDroneDto {
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
}
