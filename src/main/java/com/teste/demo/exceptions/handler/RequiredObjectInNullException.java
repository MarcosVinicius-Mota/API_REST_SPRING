package com.teste.demo.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectInNullException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public RequiredObjectInNullException(String message){
        super(message);
    }

    public RequiredObjectInNullException(){
        super("It is not allowed to persist a null object");
    }

}
