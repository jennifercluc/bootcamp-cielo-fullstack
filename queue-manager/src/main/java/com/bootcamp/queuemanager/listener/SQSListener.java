package com.bootcamp.queuemanager.listener;

import com.bootcamp.queuemanager.consumer.SQSConsumer;
import com.bootcamp.queuemanager.util.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SQSListener {

    @Value("${aws.sqs.sugestoes-url}")
    private String queueSugestaoUrl;

    @Value("${aws.sqs.elogios-url}")
    private String queueElogioUrl;

    @Value("${aws.sqs.criticas-url}")
    private String queueCriticasUrl;

    private final SQSConsumer sqsConsumer;

    @Autowired
    public SQSListener(SQSConsumer sqsConsumer) {
        this.sqsConsumer = sqsConsumer;
    }

    @SqsListener(value="ada_cielo_sugestao", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listenSugestaoQueue(String message) {

        System.out.println("[LISTENER]" + message);
        //sqsConsumer.execute(Type.SUGESTAO, queueSugestaoUrl, message);
    }

    @SqsListener(value="ada_cielo_elogio", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listenElogioQueue(String message) {

        System.out.println("[LISTENER]" + message);
        //sqsConsumer.execute(Type.ELOGIO, queueElogioUrl, message);
    }

    @SqsListener(value="${aws.sqs.criticas-name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listenCriticaQueue(String message) {
        //sqsConsumer.execute(Type.CRITICA, queueCriticasUrl, message);
    }
}