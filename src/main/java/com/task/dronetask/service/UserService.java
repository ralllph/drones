package com.task.dronetask.service;

import com.task.dronetask.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface UserService{
    UserDto regUser(UserDto user);
    UserDto findUserById(Long id);
    UserDto findByUsername(String userName);

    UserDto updateUser(Long userId,UserDto newUser);
    List<GrantedAuthority> getAuthorities(UserDto user);

    void deleteUser(Long userId);
}
