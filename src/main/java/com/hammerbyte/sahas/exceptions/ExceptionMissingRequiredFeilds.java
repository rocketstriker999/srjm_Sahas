package com.hammerbyte.sahas.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class ExceptionMissingRequiredFeilds extends RuntimeException{
    
    public ExceptionMissingRequiredFeilds(String message) {
        super(message);
    }

    public ExceptionMissingRequiredFeilds(String message, Throwable cause) {
        super(message, cause);
    }
}
