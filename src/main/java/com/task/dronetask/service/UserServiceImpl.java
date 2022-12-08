package com.task.dronetask.service;

import com.task.dronetask.converter.UserConverter;
import com.task.dronetask.dto.UserDto;
import com.task.dronetask.entity.User;
import com.task.dronetask.exception.UserNotFoundException;
import com.task.dronetask.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements  UserService{
    private UserRepository userRepo;
    private UserConverter userConverter;

    // bean created in sprigboot main app
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto regUser(UserDto user) {
        User userEntity = userConverter.userDtoToEntity(user);
        //hash the password before saving
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity = userRepo.save(userEntity);
        return userConverter.userEntityToDto(userEntity);
    }

    @Override
    public UserDto findUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return userConverter.userEntityToDto(user.get());
        }else{
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public UserDto findByUsername(String userName) {
        Optional<User> user = userRepo.findByUserName(userName);
        if(user.isPresent()){
            return userConverter.userEntityToDto(user.get());
        }
        else{
            throw  new UserNotFoundException(404L);
        }
    }
}
