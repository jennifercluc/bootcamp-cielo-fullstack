package com.bootcamp.queuemanager.service;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import com.bootcamp.queuemanager.dto.CustomerFeedbackRequestDTO;
import com.bootcamp.queuemanager.publisher.SNSPublisher;
import com.bootcamp.queuemanager.util.Status;
import com.bootcamp.queuemanager.util.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    private final SNSPublisher snsPublisher;
    private final ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> concurrentHashMap;
    private static final Logger LOG = LoggerFactory.getLogger(FeedbackService.class);

    public FeedbackService(SNSPublisher snsPublisher, ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> concurrentHashMap) {
        this.snsPublisher = snsPublisher;
        this.concurrentHashMap = concurrentHashMap;
    }

    public void sendFeedback(CustomerFeedbackRequestDTO feedback) {
        snsPublisher.sendNotification(feedback);
    }

    private ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> getAllFeedbacks() {
        return concurrentHashMap;
    }

    public int getQueueSize(String type) {
        return concurrentHashMap.get(Type.valueOf(type)).stream()
                    .filter(feedback -> !feedback.getStatus().equals(Status.FINALIZADO))
                    .mapToInt(feedback -> 1)
                    .sum();
    }

    public LinkedList<CustomerFeedbackDTO> getQueueInformation(String type) {
        return concurrentHashMap.get(Type.valueOf(type)).stream()
                .filter(feedback -> !feedback.getStatus().equals(Status.FINALIZADO))
                .collect(Collectors.toCollection(LinkedList::new));
    }

}
