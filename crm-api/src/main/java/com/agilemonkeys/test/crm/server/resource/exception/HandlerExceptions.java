package com.agilemonkeys.test.crm.server.resource.exception;

import com.agilemonkeys.test.crm.commons.exception.EntityNotFoundCRMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler {

    Logger log = LoggerFactory.getLogger(HandlerExceptions.class);

    @ExceptionHandler(EntityNotFoundCRMException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundCRMException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "Customer with ID [" + ex.getIdCustomer() + "] not found",
                ex.getMessage());

        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleExceptionn(Exception ex) {

        log.error(ex.getMessage(),ex);

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Genenal Error",
                "Cause :" + ex.getCause() + " Message :" + ex.getMessage());

        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }



}
