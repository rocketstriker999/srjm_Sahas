package com.hammerbyte.sahas.exceptions;


public class ExceptionInvalidCredentials extends RuntimeException{
    
    public ExceptionInvalidCredentials(String message) {
        super(message);
    }

    public ExceptionInvalidCredentials(String message, Throwable cause) {
        super(message, cause);
    }
}
