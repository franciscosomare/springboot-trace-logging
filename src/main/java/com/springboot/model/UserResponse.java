package com.springboot.model;

public class UserResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserResponse(String message) {
        super();
        this.message = message;
    }

    public UserResponse() {
        super();
    }
}