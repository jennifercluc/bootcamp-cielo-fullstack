package com.bootcamp.queuemanager.dto;

import com.bootcamp.queuemanager.util.Status;
import com.bootcamp.queuemanager.util.Type;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerFeedbackDTO {
    private String id;
    private Status status;
    private Type type;
    private String message;

    @JsonCreator
    public CustomerFeedbackDTO(){
        this.id = UUID.randomUUID().toString();
    };
    public CustomerFeedbackDTO(@JsonProperty("status") String status, 
                               @JsonProperty("type") String type, 
                               @JsonProperty("Message") String message) {
        this.id = UUID.randomUUID().toString();
        this.status = Status.valueOf(status.toUpperCase());
        this.type = Type.valueOf(type.toUpperCase());
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonGetter("status")
    public Status getStatus() {
        return status;
    }

    @JsonSetter("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonGetter("type")
    public Type getType() {
        return type;
    }

    @JsonSetter("type")
    public void setType(Type type) {
        this.type = type;
    }

    @JsonGetter("Message")
    public String getMessage() {
        return message;
    }

    @JsonSetter("Message")
    public void setMessage(String message) {
        this.message = message;
    }
}
