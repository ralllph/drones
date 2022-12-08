package com.task.dronetask.converter;

import com.task.dronetask.dto.UserDto;
import com.task.dronetask.entity.User;

public interface UserConverter {
    UserDto userEntityToDto(User user);
    User userDtoToEntity(UserDto user);
}
