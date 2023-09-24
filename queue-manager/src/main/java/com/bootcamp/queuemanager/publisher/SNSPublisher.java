package com.bootcamp.queuemanager.publisher;

import com.bootcamp.queuemanager.dto.CustomerFeedbackRequestDTO;
import com.bootcamp.queuemanager.exception.BadRequestException;
import com.bootcamp.queuemanager.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Component
public class SNSPublisher {
    @Autowired
    private SnsClient snsClient;

    @Value("${aws.sns.sugestoes-arn}")
    private String snsTopicSugestoes;
    @Value("${aws.sns.elogios-arn}")
    private String snsTopicElogios;
    @Value("${aws.sns.criticas-arn}")
    private String snsTopicCriticas;

    private static final Logger LOG = LoggerFactory.getLogger(FeedbackService.class);

    public SNSPublisher(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public void sendNotification(CustomerFeedbackRequestDTO feedback) {
        String snsArn;
        switch (feedback.getType()){
            case SUGESTAO -> snsArn = snsTopicSugestoes;
            case ELOGIO -> snsArn = snsTopicElogios;
            case CRITICA -> snsArn = snsTopicCriticas;
            default -> throw new IllegalArgumentException("Invalid Feedback Type! " +
                    "Consider SUGESTAO, ELOGIO or CRITICA.");
        }

        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(snsArn)
                .messageGroupId("DEFAULT-GROUP-ID")
                .message(feedback.getMessage())
                .build();

        LOG.info("[PUBLISHER] Sending Message to {} SNS. Message: {}",
                feedback.getType(), feedback.getMessage());

        PublishResponse response = snsClient.publish(publishRequest);
    }
}
