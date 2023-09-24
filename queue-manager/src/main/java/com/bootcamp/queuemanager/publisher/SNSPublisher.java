package com.bootcamp.queuemanager.publisher;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import com.bootcamp.queuemanager.dto.CustomerFeedbackRequestDTO;
import com.bootcamp.queuemanager.service.FeedbackService;
import com.bootcamp.queuemanager.util.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Component
@RequiredArgsConstructor
public class SNSPublisher {

    private final SnsClient snsClient;
    private final ObjectMapper objectMapper;

    @Value("${aws.sns.sugestoes-arn}")
    private String snsTopicSugestoes;
    @Value("${aws.sns.elogios-arn}")
    private String snsTopicElogios;
    @Value("${aws.sns.criticas-arn}")
    private String snsTopicCriticas;

    private static final Logger LOG = LoggerFactory.getLogger(FeedbackService.class);

    @SneakyThrows
    public void sendNotification(CustomerFeedbackRequestDTO requestDTO) {
        String snsArn;
        switch (requestDTO.getType()){
            case SUGESTAO -> snsArn = snsTopicSugestoes;
            case ELOGIO -> snsArn = snsTopicElogios;
            case CRITICA -> snsArn = snsTopicCriticas;
            default -> throw new IllegalArgumentException("Invalid Feedback Type! " +
                    "Consider SUGESTAO, ELOGIO or CRITICA.");
        }

        CustomerFeedbackDTO feedback = buildCustomerFeedbackDTO(requestDTO);
        String message = objectMapper.writeValueAsString(feedback);

        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(snsArn)
                .messageGroupId("DEFAULT-GROUP-ID")
                .message(message)
                .build();

        LOG.info("[PUBLISHER] Sending Message to {} SNS. Message: {}",
                requestDTO.getType(), requestDTO.getMessage());

        snsClient.publish(publishRequest);
    }

    @SneakyThrows
    public CustomerFeedbackDTO buildCustomerFeedbackDTO(CustomerFeedbackRequestDTO customerFeedbackRequestDTO) {
        CustomerFeedbackDTO feedback = new CustomerFeedbackDTO();

        feedback.setType(customerFeedbackRequestDTO.getType());
        feedback.setMessage(customerFeedbackRequestDTO.getMessage());
        feedback.setStatus(Status.RECEBIDO);

        return feedback;

    }
}
