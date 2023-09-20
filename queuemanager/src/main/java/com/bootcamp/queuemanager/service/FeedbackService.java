package com.bootcamp.queuemanager.service;

import com.bootcamp.queuemanager.model.CustomerFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Service
public class FeedbackService {

    @Autowired
    private SnsClient snsClient;

    @Value("${aws.sns.sugestoes-arn}")
    private String snsTopicSugestoes;

    @Value("${aws.sns.elogios-arn}")
    private String snsTopicElogios;

    @Value("${aws.sns.criticas-arn}")
    private String snsTopicCriticas;

    public void enviaFeedback(CustomerFeedback feedback) {
        String snsArn;
        switch (feedback.getType()){
            case SUGESTAO -> snsArn = snsTopicSugestoes;
            case ELOGIO -> snsArn = snsTopicElogios;
            case CRITICA -> snsArn = snsTopicCriticas;
            default -> snsArn = "";
        }

        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(snsArn)
                .message(feedback.getMessage())
                .build();

        PublishResponse publishResponse = snsClient.publish(publishRequest);
        feedback.setId(publishResponse.messageId());
    }
}
