package com.bootcamp.queuemanager.model;

import com.bootcamp.queuemanager.util.Type;

public class CustomerFeedbackRequest {
    private Type type;
    private String message;

    public CustomerFeedbackRequest(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
