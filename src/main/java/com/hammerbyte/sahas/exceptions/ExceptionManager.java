package com.hammerbyte.sahas.exceptions;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.hammerbyte.sahas.models.ModelException;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(ExceptionInvalidDetails.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ModelException handleExceptionIncorrectDetails(ExceptionInvalidDetails ex) {
        return new ModelException("Incorrect Details", ex.getMessage());
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class,ExceptionMissingRequiredFeilds.class}   )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ModelException handleExceptionMissingRequiredFeilds(RuntimeException ex) {
        return new ModelException("Missing Feilds", ex instanceof HttpMessageNotReadableException ?"Missing Request Body":ex.getMessage());
    }

    @ExceptionHandler(value = { ExceptionInvalidCredentials.class, ExceptionInvalidJWT.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ModelException handleExceptionInvalidCredentials(RuntimeException ex) {
        return new ModelException("Authentication Failed", ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ModelException handleExceptionResourceNotFound(ResourceNotFoundException ex) {
        return new ModelException("Resource not found", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ModelException handleExceptionGeneral(Exception ex) {
        return new ModelException("An error occurred", ex.getMessage());
    }
}