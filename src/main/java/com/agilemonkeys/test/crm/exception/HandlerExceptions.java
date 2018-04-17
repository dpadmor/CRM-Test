package com.agilemonkeys.test.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundCRMException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundCRMException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "Customer with ID [" + ex.getIdCustomer() + "] not found",
                ex.getMessage());

        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }

}
