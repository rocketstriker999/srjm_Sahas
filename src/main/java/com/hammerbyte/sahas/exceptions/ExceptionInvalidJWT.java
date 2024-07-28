package com.hammerbyte.sahas.exceptions;



public class ExceptionInvalidJWT extends RuntimeException {

    public ExceptionInvalidJWT(String message) {
        super(message);
    }

    public ExceptionInvalidJWT(String message, Throwable cause) {
        super(message, cause);
    }
}
