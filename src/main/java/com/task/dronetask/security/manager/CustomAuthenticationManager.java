package com.task.dronetask.security.manager;

import com.task.dronetask.dto.UserDto;
import com.task.dronetask.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

//THIS IS WHERE WE PASS THE OBJECT CREATED BY AUTH FILTER TOO
//it handles the actual authentication priocess
@Component
@Slf4j
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    //method helps authenticate
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //when this authenticate method is called grab the use ny username
        //authenticatiion.getName retrieves the username from authentication object i.e principal parameter
        //user service already handles if username is not fund. although its user service the error is thrown here in a filter
        //which means Checkexception filter handles it
        UserDto user = userService.findByUsername(authentication.getName());
        log.info("auth name {}",authentication.getName());
        log.info("user {}", user);
        //check the password for the user if it's not the same as the hashed password we stored when they register
        //the credentials was the second parameter after principal to store password
        //credentials need to be in string format
        log.info("password {}", user.getPassword());
        log.info("DATABASE PASS {}", authentication.getCredentials().toString());
        log.info("bcrypt result: {}", bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword()));
        if( bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())){
            //reaching here means their details are correct so give them a new token
            //which takes their name and password as parameters. Since they are verified you can get it from
            //aurthentication or user
            //return new UsernamePasswordAuthenticationToken(authentication.getName(),authentication.getCredentials());
            List<GrantedAuthority> authorities = userService.getAuthorities(user);
            return new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(),authorities);

        }else {
            //Bad credentials exception is handled by spring security
            //Note that this method is called in Authentication filter and if this exception is thrown
            //bad credentials is an Authentication exception so when thrown in Authentication filter
            //The class automatically calls unsuccessfulAuth method in Authentication filter class
            log.info("wrong passcode");
            throw new BadCredentialsException("Wrong password");
        }
    }
}
