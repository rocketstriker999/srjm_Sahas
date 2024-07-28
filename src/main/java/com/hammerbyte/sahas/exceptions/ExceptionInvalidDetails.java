package com.hammerbyte.sahas.exceptions;


public class ExceptionInvalidDetails extends RuntimeException{
    
    public ExceptionInvalidDetails(String message) {
        super(message);
    }

    public ExceptionInvalidDetails(String message, Throwable cause) {
        super(message, cause);
    }
}
