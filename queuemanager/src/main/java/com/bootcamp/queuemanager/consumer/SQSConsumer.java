package com.bootcamp.queuemanager.consumer;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import com.bootcamp.queuemanager.util.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SQSConsumer {

    private final SqsClient sqsClient;
    private final ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> sqsDataStorage;
    private static final Logger LOG = LoggerFactory.getLogger(SQSConsumer.class);

    @Autowired
    public SQSConsumer(SqsClient sqsClient, ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> sqsDataStorage) {
        this.sqsClient = sqsClient;
        this.sqsDataStorage = sqsDataStorage;
    }

    public void processMessages(String queueUrl) {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(1)
                .build();

        try{
            ReceiveMessageResponse receiveMessageResponse = sqsClient.receiveMessage(receiveMessageRequest);
            Message message = receiveMessageResponse.messages().stream().findFirst().orElseThrow();
            LOG.info("[PUBLISHER] Message from Queue. Message: {}",  message);
            LOG.info("[PUBLISHER] Message from Queue. REQUEST: {}",  receiveMessageRequest);

            process(queueUrl, message);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private void process(String queueUrl, Message message) {
        String messageId = message.messageId();

        try {
            Thread.sleep(20000); // 20 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        deleteProcessedMessage(queueUrl, message);
        System.out.println("Mensagem processada com sucesso: " + messageId);
    }

    private void updateLocalStorage(){

    }
    private void deleteProcessedMessage(String queueUrl, Message message) {
        sqsClient.deleteMessage(builder -> builder.queueUrl(queueUrl).receiptHandle(message.receiptHandle()));
    }
}
