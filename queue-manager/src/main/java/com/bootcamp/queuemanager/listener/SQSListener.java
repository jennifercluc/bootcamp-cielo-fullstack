package com.bootcamp.queuemanager.listener;

import com.bootcamp.queuemanager.consumer.SQSConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class SQSListener {

    @Value("${aws.sqs.sugestoes-url}")
    private String queueSugestaoUrl;

    @Value("${aws.sqs.elogios-url}")
    private String queueElogioUrl;

    @Value("${aws.sqs.criticas-url}")
    private String queueCriticasUrl;

    private final SQSConsumer sqsConsumer;

    @SqsListener(value="ada_cielo_sugestao.fifo", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listenSugestaoQueue(String message, @Headers Map<String,String> headers) {

        System.out.println("[LISTENER]" + message);
        //sqsConsumer.execute(Type.SUGESTAO, queueSugestaoUrl, message);
    }

    @SqsListener(value="${aws.sqs.elogios-name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listenElogioQueue(String message) {

        System.out.println("[LISTENER]" + message);
        //sqsConsumer.execute(Type.ELOGIO, queueElogioUrl, message);
    }

    @SqsListener(value="${aws.sqs.criticas-name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listenCriticaQueue(String message) {
        //sqsConsumer.execute(Type.CRITICA, queueCriticasUrl, message);
    }
}