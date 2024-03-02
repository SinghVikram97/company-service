package com.vikram.companyservice.exception;

public class DownstreamServiceException extends RuntimeException{
    private final String serviceName;
    private final int statusCode;
    private final String exceptionMessage;

    public DownstreamServiceException(String serviceName, int statusCode, String exceptionMessage) {
        super(String.format("Exception from  %s service with status code with %d and exception message : %s", serviceName, statusCode, exceptionMessage));
        this.serviceName = serviceName;
        this.statusCode = statusCode;
        this.exceptionMessage = exceptionMessage;
    }
}
