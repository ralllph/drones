package com.task.dronetask.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
//this regMed dto is for us not to have to return Drones Dto
@Getter
@Setter
public class RegMedDto {
    private Long id;

    @NotBlank(message = "name can't be blank")
    private String name;

    @NotNull(message = "weight can't be blank")
    private BigDecimal weight;

    @NotBlank(message = "code can't be blank")
    private String code;

    @NotBlank(message = "image can't be blank")
    private String image;

    // we didn't include drones due to get medication by drone
}
