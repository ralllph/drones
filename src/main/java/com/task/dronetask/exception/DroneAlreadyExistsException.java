package com.task.dronetask.exception;
//extending runtime exception is best practice if you don't want to handle exception everywhere it applies
public class DroneAlreadyExistsException extends RuntimeException{
    public DroneAlreadyExistsException(){
        super("Drone already exists");
    }
}
