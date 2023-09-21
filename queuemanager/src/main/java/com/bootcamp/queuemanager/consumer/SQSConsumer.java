package com.bootcamp.queuemanager.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

@Component
public class SQSConsumer {

    private final SqsClient sqs;

    @Autowired
    public SQSConsumer(SqsClient sqs) {
        this.sqs = sqs;
    }

    public void processMessages(String queueUrl) {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(1)
                .build();

        try{
            ReceiveMessageResponse receiveMessageResponse = sqs.receiveMessage(receiveMessageRequest);
            Message message = receiveMessageResponse.messages().stream().findFirst().orElseThrow();

            process(message);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private void process(Message message) {
        String messageId = message.messageId();

        try {
            Thread.sleep(20000); // 20 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Mensagem processada com sucesso: " + messageId);
    }
}
