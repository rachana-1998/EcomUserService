package com.rachana.EcomUserService.exception;

public class InvalidCredientialException extends  RuntimeException{

    public InvalidCredientialException() {
    }

    public InvalidCredientialException(String message) {
        super(message);
    }

    public InvalidCredientialException(String message, Throwable cause) {
        super(message, cause);
    }
}
