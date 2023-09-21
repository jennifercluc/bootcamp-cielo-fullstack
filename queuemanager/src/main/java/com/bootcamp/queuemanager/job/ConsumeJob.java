package com.bootcamp.queuemanager.job;

import com.bootcamp.queuemanager.consumer.SQSConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;

@Component
@EnableScheduling
public class ConsumeJob {

    private SQSConsumer sqsConsumer;

    @Autowired
    public ConsumeJob(SQSConsumer sqsConsumer) {
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