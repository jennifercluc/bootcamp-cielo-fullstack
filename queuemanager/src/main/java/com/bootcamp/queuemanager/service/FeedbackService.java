package com.bootcamp.queuemanager.service;

import com.bootcamp.queuemanager.model.CustomerFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
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

    public FeedbackService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

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
                .messageGroupId("DEFAULT")
                .message(feedback.getMessage())
                .build();

        PublishResponse response = snsClient.publish(publishRequest);
    }

    private void buildSnsClient() {
        this.snsClient = SnsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}
