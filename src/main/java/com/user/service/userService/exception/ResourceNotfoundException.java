package com.user.service.userService.exception;

public class ResourceNotfoundException extends RuntimeException{

    public ResourceNotfoundException(){
        super("resource not found on server");
    }
    public ResourceNotfoundException(String message){
        super(message);
    }
}
