package com.task.dronetask.exception;
//extending runtime exception is best practice if you don't want to handle exception everywhere it applies
public class DroneNotFoundException extends RuntimeException{
    public DroneNotFoundException(Long id){
        super("The drone id" + " "+ id + " " +  "does not exist in our record");
    }
}
