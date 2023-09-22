package com.bootcamp.queuemanager.dto;

import com.bootcamp.queuemanager.util.Type;

public class CustomerFeedbackRequestDTO {
    private Type type;
    private String message;

    public CustomerFeedbackRequestDTO(Type type, String message) {
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
