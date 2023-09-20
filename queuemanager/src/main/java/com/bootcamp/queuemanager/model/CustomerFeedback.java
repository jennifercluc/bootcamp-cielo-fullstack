package com.bootcamp.queuemanager.model;

import com.bootcamp.queuemanager.util.Status;
import com.bootcamp.queuemanager.util.Type;

import java.util.UUID;

public class CustomerFeedback {
    private String id;
    private Type type;
    private Status status;
    private String message;

    public CustomerFeedback(Long id, Type type, Status status, String message) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.status = status;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
