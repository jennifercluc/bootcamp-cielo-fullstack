package com.bootcamp.queuemanager.consumer;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import com.bootcamp.queuemanager.util.Status;
import com.bootcamp.queuemanager.util.Type;
import com.bootcamp.queuemanager.util.Utilities;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class SQSConsumer {

    @Value("${aws.sqs.sugestoes-url}")
    private String queueSugestaoUrl;

    @Value("${aws.sqs.elogios-url}")
    private String queueElogioUrl;

    @Value("${aws.sqs.criticas-url}")
    private String queueCriticasUrl;

    private final SqsClient sqsClient;
    private static final Logger LOG = LoggerFactory.getLogger(SQSConsumer.class);
    private final ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> concurrentHashMap;

    /* Consome, processa e remove a mensagem "da vez" na fila SQS. */
    public void execute(Type type) {
        String url = getUrlFromType(type);

        LOG.info("[CONSUMER] EXECUTE: {}",  url);
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(url)
                .maxNumberOfMessages(1)
                .build();

        try{
            ReceiveMessageResponse receiveMessageResponse = sqsClient.receiveMessage(receiveMessageRequest);
            Message message = receiveMessageResponse.messages().stream().findFirst().orElseThrow();
            LOG.info("[CONSUMER] Message from Queue. Message: {}",  message);

            CustomerFeedbackDTO feedbackDTO = Utilities.messageToDTO(message);
            addFeedbackToLocalStorage(feedbackDTO);
            process(url, message, feedbackDTO);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    /* Processa o feedback e altera seu status para FINALIZADO. */
    private void process(String queueUrl, Message message, CustomerFeedbackDTO feedbackDTO) {
        String messageId = message.messageId();

        try {
            Thread.sleep(10000); // 10 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        LOG.info("[CONSUMER] URL: {} | MESSAGE ID",  queueUrl, message.messageId());

        updateFeedbackStatusStored(feedbackDTO, Status.EM_PROCESSAMENTO);
        LOG.info("[CONSUMER] Feedback EM PROCESSAMENTO: {}", messageId);
        LOG.info("[CONSUMER] Fila de ELOGIO: {}", concurrentHashMap.get(Type.ELOGIO).size());
        LOG.info("[CONSUMER] Fila de CRITICA: {}", concurrentHashMap.get(Type.CRITICA).size());
        LOG.info("[CONSUMER] Fila de SUGESTAO: {}", concurrentHashMap.get(Type.SUGESTAO).size());

        try {
            Thread.sleep(10000); // 10 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        updateFeedbackStatusStored(feedbackDTO, Status.FINALIZADO);
        LOG.info("[CONSUMER] Fila de ELOGIO: {}", concurrentHashMap.get(Type.ELOGIO).size());
        LOG.info("[CONSUMER] Fila de CRITICA: {}", concurrentHashMap.get(Type.CRITICA).size());
        LOG.info("[CONSUMER] Fila de SUGESTAO: {}", concurrentHashMap.get(Type.SUGESTAO).size());

        deleteProcessedMessage(queueUrl, message);
        LOG.info("[CONSUMER] Feedback FINALIZADO: {}", messageId);
    }

    /* Remove a mensagem processada da fila SQS. */
    private void deleteProcessedMessage(String queueUrl, Message message) {
        sqsClient.deleteMessage(builder -> builder.queueUrl(queueUrl).receiptHandle(message.receiptHandle()));
    }

    private void updateFeedbackStatusStored(CustomerFeedbackDTO feedbackDTO, Status newStatus) {
        concurrentHashMap.get(feedbackDTO.getType()).stream()
                .filter(feedback -> {
                    if (feedback.getId().equals(feedbackDTO.getId())) feedback.setStatus(newStatus);
                    return true;
                });
    }

    private void addFeedbackToLocalStorage(CustomerFeedbackDTO feedbackDTO){
        feedbackDTO.setStatus(Status.RECEBIDO);
        LOG.info("[CONSUMER] Feedback RECEBIDO: {}", feedbackDTO.getId());
        concurrentHashMap.get(feedbackDTO.getType()).add(feedbackDTO);

        LOG.info("[CONSUMER] Fila de ELOGIO: {}", concurrentHashMap.get(Type.ELOGIO).size());
        LOG.info("[CONSUMER] Fila de CRITICA: {}", concurrentHashMap.get(Type.CRITICA).size());
        LOG.info("[CONSUMER] Fila de SUGESTAO: {}", concurrentHashMap.get(Type.SUGESTAO).size());
    }

    private String getUrlFromType(Type type) {
        String queueUrl;
        switch (type){
            case ELOGIO -> queueUrl = queueElogioUrl;
            case CRITICA -> queueUrl = queueCriticasUrl;
            case SUGESTAO -> queueUrl = queueSugestaoUrl;
            default -> throw new IllegalArgumentException("Invalid Feedback Type! " +
                    "Consider SUGESTAO, ELOGIO or CRITICA.");
        }
        return queueUrl;
    }
}
