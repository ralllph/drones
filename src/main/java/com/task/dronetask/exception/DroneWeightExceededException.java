package com.task.dronetask.exception;

public class DroneWeightExceededException  extends RuntimeException{
    public DroneWeightExceededException(Long id){
        super("Error: Attempting to overweigh drone:" + " " + id);
    }
}
