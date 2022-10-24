package com.example.employee_administration.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception{

    private static final long serialVersionUID = 1L;

    private String errorMessage;

    private Integer httpStatusCode;


    public ServiceException(String errorMessage, Integer httpStatusCode) {
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
    }

    public ServiceException(String errorMessage, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.httpStatusCode = status == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : status.value();
    }

    public ServiceException(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
