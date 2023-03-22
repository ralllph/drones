package com.task.dronetask.service;

import com.task.dronetask.converter.UserConverter;
import com.task.dronetask.dto.UserDto;
import com.task.dronetask.entity.User;
import com.task.dronetask.exception.UserNotFoundException;
import com.task.dronetask.repository.UserRepository;
import com.task.dronetask.security.SpringSecurityUser.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements  UserService, UserDetailsService {
    private UserRepository userRepo;
    private UserConverter userConverter;

    // bean created in sprigboot main app
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto regUser(UserDto user) {
        User userEntity = userConverter.userDtoToEntity(user);
        //hash the password before saving
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        User savedUser = userRepo.save(userEntity);
        return userConverter.userEntityToDto(savedUser);
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(username);
        if(!user.isPresent()){
            throw  new UserNotFoundException(404L);
        }
        User userFound = user.get();
        return  new MyUserDetails(userFound.getUserName(), userFound.getPassword());
    }

    public List<GrantedAuthority>  getAuthorities(UserDto user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
