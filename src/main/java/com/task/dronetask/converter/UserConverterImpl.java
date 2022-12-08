package com.task.dronetask.converter;

import com.task.dronetask.dto.UserDto;
import com.task.dronetask.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl  implements  UserConverter{
    @Override
    public UserDto userEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    @Override
    public User userDtoToEntity(UserDto user) {
        User userEntity = new User();
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }
}
