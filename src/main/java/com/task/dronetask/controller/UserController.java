package com.task.dronetask.controller;

import com.task.dronetask.dto.UserDto;
import com.task.dronetask.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
//Tag is used in open api documentation
@Tag(name = "User Controller", description = "Create user and find user by Id")
public class UserController {
    private UserService userService;

    @PostMapping("/regUser")
    @Operation(summary = "Register user", description = "register a user to have secure access")
    public ResponseEntity registerUser(@RequestBody  @Valid UserDto user){
        userService.regUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/findUser/{id}")
    @Operation(summary = "Find user", description = "Find a registered user by Id")
    public ResponseEntity<String> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findUserById(id).getUserName(),HttpStatus.OK);
    }

    @PutMapping("/updateUser/user/{userId}")
    public ResponseEntity updateUser(@PathVariable Long userId, @RequestBody UserDto newUserBody){
        userService.updateUser(userId, newUserBody);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/user/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
