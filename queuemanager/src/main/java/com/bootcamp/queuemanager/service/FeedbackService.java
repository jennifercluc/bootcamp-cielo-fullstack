package com.bootcamp.queuemanager.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishResult;
import com.bootcamp.queuemanager.model.CustomerFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//import com.amazonaws.services.sns.model.PublishRequest;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Service
public class FeedbackService {

    @Autowired
    private AmazonSNS amazonSNS;

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

        /*PublishRequest publishRequest = new PublishRequest()
                .withTopicArn(snsArn)
                .withMessage(feedback.getMessage());*/

        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(snsArn)
                .messageGroupId(feedback.getId())
                .message(feedback.getMessage())
                .build();

        buildSnsClient();
        PublishResponse response = snsClient.publish(publishRequest);


        //PublishResult publishResult = amazonSNS.publish(publishRequest);
        System.out.println("ID: " + response.messageId() + " | Mensagem: " + response.toString());
    }

    private void buildSnsClient() {
        this.snsClient = SnsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}
