package com.bootcamp.queuemanager.service;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import com.bootcamp.queuemanager.dto.CustomerFeedbackRequestDTO;
import com.bootcamp.queuemanager.publisher.SNSPublisher;
import com.bootcamp.queuemanager.util.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FeedbackService {

    private final SNSPublisher snsPublisher;
    private final ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> sqsDataStorage;
    private static final Logger LOG = LoggerFactory.getLogger(FeedbackService.class);

    public FeedbackService(SNSPublisher snsPublisher, ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> sqsDataStorage) {
        this.snsPublisher = snsPublisher;
        this.sqsDataStorage = sqsDataStorage;
    }

    public void sendFeedback(CustomerFeedbackRequestDTO feedback) {
        snsPublisher.sendNotification(feedback);
    }

    private int getSQSSize(Character type) {
        return sqsDataStorage.get(type).size();
    }
    private Map<Type, LinkedList<CustomerFeedbackDTO>> getSQSData() {
        return sqsDataStorage;
    }

    public int getQueueSize(String type) {
        return sqsDataStorage.get(Type.valueOf(type)).size();
    }

    public LinkedList<CustomerFeedbackDTO> getQueueInformation(String type) {
        return sqsDataStorage.get(Type.valueOf(type));
    }

}
