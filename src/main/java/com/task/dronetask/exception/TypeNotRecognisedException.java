package com.task.dronetask.exception;

public class TypeNotRecognisedException extends RuntimeException{
    public TypeNotRecognisedException(String type){
        super("The type" + " "+ type + " " +  "is not recognised");
    }
}
