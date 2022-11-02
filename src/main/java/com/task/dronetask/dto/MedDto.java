package com.task.dronetask.dto;

import com.task.dronetask.entity.Drones;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
public class MedDto {
    private Long id;

    @NotBlank(message = "name can't be blank")
    //allows only letters,numbers,-,_
    @Pattern(regexp = "([A-Za-z0-9\\-\\_]+)", message = "remove invalid character(s)")
    private String name;

    @NotNull(message = "weight can't be blank")
    private BigDecimal weight;

    @NotBlank(message = "code can't be blank")
    //allows only uppercase, _ and numbers
    @Pattern(regexp = "([A-Z0-9\\_]+)", message = "remove invalid character(s)")
    private String code;

    @NotBlank(message = "image can't be blank")
    private String image;


    private Drones drone;
}
