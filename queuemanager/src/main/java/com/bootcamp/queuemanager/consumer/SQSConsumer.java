package com.bootcamp.queuemanager.consumer;

import com.amazonaws.services.guardduty.model.Feedback;
import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import com.bootcamp.queuemanager.util.Status;
import com.bootcamp.queuemanager.util.Type;
import com.bootcamp.queuemanager.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SQSConsumer {

    @Value("${aws.sqs.sugestoes-url}")
    private String queueSugestaoUrl;

    @Value("${aws.sqs.elogios-url}")
    private String queueElogioUrl;

    @Value("${aws.sqs.criticas-url}")
    private String queueCriticasUrl;

    private final SqsClient sqsClient;
    private final ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> concurrentHashMap;
    private static final Logger LOG = LoggerFactory.getLogger(SQSConsumer.class);

    @Autowired
    public SQSConsumer(SqsClient sqsClient, ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> concurrentHashMap) {
        this.sqsClient = sqsClient;
        this.concurrentHashMap = concurrentHashMap;
    }

    /* Consome, processa e remove a mensagem "da vez" na fila SQS. */
    public void execute(Type type) {
        String queueUrl = getUrlFromType(type);

        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(1)
                .build();

        try{
            ReceiveMessageResponse receiveMessageResponse = sqsClient.receiveMessage(receiveMessageRequest);
            Message message = receiveMessageResponse.messages().stream().findFirst().orElseThrow();
            LOG.info("[CONSUMER] Message from Queue. Message: {}",  message);

            CustomerFeedbackDTO feedbackDTO = Utilities.messageToDTO(message);
            addFeedbackToLocalStorage(feedbackDTO);
            process(queueUrl, message, feedbackDTO);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    /* Processa o feedback e altera seu status para FINALIZADO. */
    private void process(String queueUrl, Message message, CustomerFeedbackDTO feedbackDTO) {
        String messageId = message.messageId();

        try {
            Thread.sleep(20000); // 20 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        LOG.info("[CONSUMER] URL: {} // MESSAGE: {}",  queueUrl, message);
        LOG.info("[CONSUMER] FEEDBACK DTO: {}",  feedbackDTO);

        updateFeedbackStatusStored(feedbackDTO, Status.EM_PROCESSAMENTO);

        try {
            Thread.sleep(20000); // 20 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        updateFeedbackStatusStored(feedbackDTO, Status.FINALIZADO);

        deleteProcessedMessage(queueUrl, message);
        System.out.println("Feedback processado com sucesso: " + messageId);
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
        /*LinkedList<CustomerFeedbackDTO> feedbacks = concurrentHashMap.get(feedbackDTO.getType());
        Type fila = feedbackDTO.getType();
        feedbacks.add(feedbackDTO);
        concurrentHashMap.put(fila, feedbacks);*/

        concurrentHashMap.get(feedbackDTO.getType()).add(feedbackDTO);
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
