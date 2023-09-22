package com.bootcamp.queuemanager.job;

import com.bootcamp.queuemanager.consumer.SQSConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ConsumerJob {

    private final SQSConsumer sqsConsumer;

    @Autowired
    public ConsumerJob(SQSConsumer sqsConsumer) {
        this.sqsConsumer = sqsConsumer;
    }

    @Value("${aws.sqs.sugestoes-url}")
    private String queueSugestaoUrl;

    @Value("${aws.sqs.elogios-url}")
    private String queueElogioUrl;

    @Value("${aws.sqs.criticas-url}")
    private String queueCriticasUrl;

    @Scheduled(fixedRate = 15000)
    public void consumirSQSs() {
        sqsConsumer.processMessages(queueSugestaoUrl);
        sqsConsumer.processMessages(queueElogioUrl);
        sqsConsumer.processMessages(queueCriticasUrl);
    }


}