package com.task.dronetask.dto;

import com.task.dronetask.enums.Model;
import com.task.dronetask.enums.State;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
// sending back drone to user
// this dto is just to prevent us from returning medications when returning DRONES to the user
public class RegDroneDto {
    private Long id;

    private String serialNumber;

    private Model model;

    private BigDecimal weightLimit;

    private int batteryCapacity;

    private State state;
}
