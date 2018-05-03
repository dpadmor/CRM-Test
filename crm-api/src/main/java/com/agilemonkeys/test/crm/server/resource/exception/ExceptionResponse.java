package com.agilemonkeys.test.crm.server.resource.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class ExceptionResponse {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime dateError;

    private String message;

    private String detail;

    private String idError;

    public ExceptionResponse(LocalDateTime dateError, HttpStatus status, String message, String detail) {
        this.status = status;
        this.dateError = dateError;
        this.message = message;
        this.detail = detail;
        this.idError = UUID.randomUUID().toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateError() {
        return dateError;
    }

    public void setDateError(LocalDateTime dateError) {
        this.dateError = dateError;
    }
}
