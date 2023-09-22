package com.bootcamp.queuemanager.dto;

import com.bootcamp.queuemanager.util.Status;
import com.bootcamp.queuemanager.util.Type;

public class CustomerFeedbackDTO {
    private String id;

    private Status status;
    private Type type;
    private String message;

    public CustomerFeedbackDTO(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
