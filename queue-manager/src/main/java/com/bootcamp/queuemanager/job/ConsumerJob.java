package com.bootcamp.queuemanager.job;

import com.bootcamp.queuemanager.consumer.SQSConsumer;
import com.bootcamp.queuemanager.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ConsumerJob {
    private final SQSConsumer sqsConsumer;

    @Autowired
    public ConsumerJob(SQSConsumer sqsConsumer) {
        this.sqsConsumer = sqsConsumer;
    }

    //@Scheduled(fixedRate = 15000)
    public void consumirSQSs() {
        sqsConsumer.execute(Type.SUGESTAO);
        sqsConsumer.execute(Type.ELOGIO);
        sqsConsumer.execute(Type.CRITICA);
    }
}