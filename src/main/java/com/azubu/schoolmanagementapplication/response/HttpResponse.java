package com.azubu.schoolmanagementapplication.response;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class HttpResponse {

    protected String timeStamp;

    protected int statusCode;

    protected HttpStatus httpStatus;

    protected String message;


    public HttpResponse(String timeStamp, int statusCode, HttpStatus httpStatus, String message) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
