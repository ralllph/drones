package com.task.dronetask.converter;

import com.task.dronetask.dto.UserDto;
import com.task.dronetask.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverterImpl  implements  UserConverter{
    private final ModelMapper modelMapper;
    @Override
    public UserDto userEntityToDto(User user) {
       return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User userDtoToEntity(UserDto user) {
        return modelMapper.map(user, User.class);
    }
}
