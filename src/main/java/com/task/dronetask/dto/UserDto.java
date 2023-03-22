package com.task.dronetask.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "user name can't be blank")
    @NotNull
    private String userName;

    @NotBlank(message = "password can't be blank")
    @NotNull
    private String password;

    @Override
    public String toString() {
        return "UserDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
