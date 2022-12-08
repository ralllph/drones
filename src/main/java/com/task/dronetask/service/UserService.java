package com.task.dronetask.service;

import com.task.dronetask.dto.UserDto;

public interface UserService{
    UserDto regUser(UserDto user);
    UserDto findUserById(Long id);
    UserDto findByUsername(String userName);
}
