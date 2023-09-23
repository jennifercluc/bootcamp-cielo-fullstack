package com.bootcamp.queuemanager.service;

import com.bootcamp.queuemanager.exception.BadRequestException;
import com.bootcamp.queuemanager.model.CustomerFeedbackRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Service
public class FeedbackProducerService {

    @Autowired
    private SnsClient snsClient;

    @Value("${aws.sns.sugestoes-arn}")
    private String snsTopicSugestoes;

    @Value("${aws.sns.elogios-arn}")
    private String snsTopicElogios;

    @Value("${aws.sns.criticas-arn}")
    private String snsTopicCriticas;

    private static final Logger LOG = LoggerFactory.getLogger(FeedbackProducerService.class);

    public FeedbackProducerService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public void sendFeedback(CustomerFeedbackRequest feedback) {
        String snsArn;
        switch (feedback.getType()){
            case SUGESTAO -> snsArn = snsTopicSugestoes;
            case ELOGIO -> snsArn = snsTopicElogios;
            case CRITICA -> snsArn = snsTopicCriticas;
            default -> throw new BadRequestException("Invalid Feedback Type! " +
                    "Consider SUGESTAO, ELOGIO or CRITICA.");
        }

        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(snsArn)
                .messageGroupId("DEFAULT-GROUP-ID")
                .message(feedback.getMessage())
                .build();

        LOG.info("[PRODUCER] Sending Message to {} SNS. Message: {}",
                feedback.getType(), feedback.getMessage());

        PublishResponse response = snsClient.publish(publishRequest);

        LOG.info("[PRODUCER] SNS Response: {}", response.toString());
    }
}
