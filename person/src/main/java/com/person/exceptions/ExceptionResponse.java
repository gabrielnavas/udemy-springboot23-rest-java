package com.person.exceptions;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamps;
    private String details;
    private String message;

    public ExceptionResponse(Date timestamps, String details, String message) {
        this.timestamps = timestamps;
        this.details = details;
        this.message = message;
    }

    public Date getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Date timestamps) {
        this.timestamps = timestamps;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
