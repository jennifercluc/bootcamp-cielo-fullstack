package com.bootcamp.queuemanager.dto;

import com.bootcamp.queuemanager.util.Status;
import com.bootcamp.queuemanager.util.Type;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerFeedbackDTO {
    @JsonProperty("MessageId")
    private String id;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("type")
    private Type type;
    @JsonProperty("Message")
    private String message;

    public CustomerFeedbackDTO(){
        this.id = UUID.randomUUID().toString();
    };
    public CustomerFeedbackDTO(Status status, Type type, String message) {
        this.id = UUID.randomUUID().toString();
        this.status = status;
        this.type = type;
        this.message = message;
    }

    @JsonGetter("MessageId")
    public String getId() {
        return id;
    }

    @JsonSetter("MessageId")
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

    @JsonGetter("Message")
    public String getMessage() {
        return message;
    }

    @JsonSetter("Message")
    public void setMessage(String message) {
        this.message = message;
    }
}
