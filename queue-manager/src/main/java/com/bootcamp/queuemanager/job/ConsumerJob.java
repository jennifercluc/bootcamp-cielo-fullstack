package com.bootcamp.queuemanager.job;

import com.bootcamp.queuemanager.consumer.SQSConsumer;
import com.bootcamp.queuemanager.util.Type;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class ConsumerJob {

    private final SQSConsumer sqsConsumer;

    //@Scheduled(fixedRate = 10000)
    public void consumeSugestaoSQSs() {
        sqsConsumer.execute(Type.SUGESTAO);
    }

    //@Scheduled(fixedRate = 1000)
    public void consumeElogioSQSs() {
        sqsConsumer.execute(Type.ELOGIO);
    }

    //@Scheduled(fixedRate = 1000)
    public void consumeCriticaSQSs() {
        sqsConsumer.execute(Type.CRITICA);
    }


}
