package com.task.dronetask;

import com.task.dronetask.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//controller advice allows you to handle exceptions globally
//so anything that gets thrown will be handled right here
//WE ARE ONLY INHERIRING FROM THE RESPONSEENTITYEXCEPTIONHANDLER because we want to override methodArgumentnot valid function
//because we made validations
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    //method to handle droneNotfound exception
    //exception handler allows you map the exception to this method
    //here we handle Dronenotfound exception hence Dronenotfound.class
    @ExceptionHandler(DroneNotFoundException.class)
    //we are returning an object of error here but Object is more generic
    //the parameter is the contact not found exception
    public ResponseEntity<Object> handleDDroneNotFoundException(DroneNotFoundException exception){
        //create an object of Error response,, exception .getLocalizedMessage( ) is getting the message we passed in super in DroneNotFound exception
        //notice that our message is a list it=n in the error class so we make the error object be returned as a json array that supplies  alist
        ErrorResponse error = new ErrorResponse(Arrays.asList(exception.getLocalizedMessage()));
        //now we return the error object
        //remember response entity  and serialization where spring boot would go to errResponse and change the object to json before returning it
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DroneWeightExceededException.class)
    public ResponseEntity<Object> handleDroneWeightExceededException(DroneWeightExceededException exception){
        ErrorResponse error = new ErrorResponse(Arrays.asList(exception.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //type handleMethod ... to override
    //This method is where we handle those validations like not blank
    //when spring boot performs @valid check, the errors are packaged in a binding result which you can access here
    //through the parameter MethodArgument not valid exception
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //now we need to loop through all the errors binded to Methodargument exception in ex
        //the ex.getBinding.getallerrors is a list with a data type of Object Error which
        //is why the return type for this for loop is ObjectError
        //we create an error list where we can add ech error element from the loop
        List<String> errorList = new ArrayList<>();
        for(ObjectError error: ex.getBindingResult().getAllErrors()){
            errorList.add(error.getDefaultMessage());
        }
        //note that this is the return type when you originally override but you need to cancel and return a status of 404 bad request
        //return super.handleMethodArgumentNotValid(ex, headers, status, request);
        //now we can pass a new object containing the errorList as the parameter for the new constructor
        return new ResponseEntity<>(new ErrorResponse(errorList),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DroneUnableToLoadException.class)
    public ResponseEntity<Object> handleDroneUnableToLoadException(DroneUnableToLoadException exception){
        ErrorResponse error = new ErrorResponse(Arrays.asList(exception.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DroneAlreadyExistsException.class)
    public ResponseEntity<Object> handleDroneNameExistsException(DroneAlreadyExistsException exception){
        ErrorResponse error = new ErrorResponse(Arrays.asList(exception.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
