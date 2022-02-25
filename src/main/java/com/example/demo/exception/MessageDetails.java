package com.example.demo.exception;

import java.util.Date;

public class MessageDetails {
    private String message;
    private Date dateTime;
    private String details;

    public MessageDetails(String message, Date dateTime, String details) {
        super();
        this.message = message;
        this.dateTime = dateTime;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
