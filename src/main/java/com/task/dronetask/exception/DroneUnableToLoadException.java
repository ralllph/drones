package com.task.dronetask.exception;

public class DroneUnableToLoadException  extends RuntimeException{
    public DroneUnableToLoadException(){
        super("Drone cannot be in loading state due to battery capacity being below 25%");
    }
}
