package com.bootcamp.queuemanager.dto;

import com.bootcamp.queuemanager.util.Status;
import com.bootcamp.queuemanager.util.Type;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CustomerFeedbackDTO {
    private String id;
    private Status status;
    private Type type;
    private String message;

    private String topicArn;

    public CustomerFeedbackDTO(String id, Status status, Type type, String message, String topicArn) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.message = message;
        this.topicArn = topicArn;
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

    @JsonGetter("TopicArn")
    public String getTopicArn() {
        return topicArn;
    }

    @JsonSetter("TopicArn")
    public void setTopicArn(String topicArn) {
        this.topicArn = topicArn;
    }
}
