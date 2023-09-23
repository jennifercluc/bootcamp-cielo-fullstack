package com.bootcamp.queuemanager.consumer;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import com.bootcamp.queuemanager.util.Status;
import com.bootcamp.queuemanager.util.Type;
import com.bootcamp.queuemanager.util.Utilities;
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
import java.util.stream.Collectors;

@Component
public class SQSConsumer {

    private final SqsClient sqsClient;
    private final ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> concurrentHashMap;
    private static final Logger LOG = LoggerFactory.getLogger(SQSConsumer.class);

    @Autowired
    public SQSConsumer(SqsClient sqsClient, ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> concurrentHashMap) {
        this.sqsClient = sqsClient;
        this.concurrentHashMap = concurrentHashMap;
    }

    public void processMessages(String queueUrl) {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(1)
                .build();

        try{
            ReceiveMessageResponse receiveMessageResponse = sqsClient.receiveMessage(receiveMessageRequest);
            Message message = receiveMessageResponse.messages().stream().findFirst().orElseThrow();
            LOG.info("[CONSUMER] Message from Queue. Message: {}",  message);
            LOG.info("[CONSUMER] Message from Queue. REQUEST: {}",  receiveMessageRequest);

            CustomerFeedbackDTO feedbackDTO = Utilities.messageToDTO(message);
            feedbackDTO.setStatus(Status.EM_PROCESSAMENTO);     //EM PROCESSAMENTO
            process(queueUrl, message, feedbackDTO);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private void process(String queueUrl, Message message, CustomerFeedbackDTO feedbackDTO) {
        String messageId = message.messageId();

        try {
            Thread.sleep(20000); // 20 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        LOG.info("[CONSUMER] URL: {} // MESSAGE: {}",  queueUrl, message);
        LOG.info("[CONSUMER] FEEDBACK DTO: {}",  feedbackDTO);

        feedbackDTO.setStatus(Status.FINALIZADO);     //FINALIZADO
        deleteProcessedMessage(queueUrl, message);
        System.out.println("Feedback processado com sucesso: " + messageId);
    }

    private void updateFeedbackStatus(CustomerFeedbackDTO feedbackDTO) {
        /*LinkedList<CustomerFeedbackDTO> data = concurrentHashMap.get(feedbackDTO.getType());
        data = data.stream().map(feedback -> {
                if (feedback.getId().equals(feedbackDTO.getId()) && feedback.getStatus().getValor() < 3) {
                    //AVANCA O VALOR DO STATUS
                    feedback.setStatus(feedback.getStatus().fromValor(feedback.getStatus().getValor() + 1));
                    return feedback;
                }
            return null;
        }).collect(Collectors.toCollection(LinkedList::add));*/
    }
    private void updateLocalStorage(CustomerFeedbackDTO feedbackDTO){


        concurrentHashMap.get(feedbackDTO.getType()).add(feedbackDTO);

        /*
        feedbackList = feedbackList.stream()
                .map(feedback -> {
                    if (feedback.getId() == idPesquisado) {
                        feedback.setType(novoTipo); // Atualize o tipo se o ID for encontrado
                    }
                    return feedback;
                })
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);

        // Se o ID não foi encontrado, adicione um novo elemento
        if (!existeId(feedbackList, idPesquisado)) {
            feedbackList.add(new CustomerFeedbackDTO(idPesquisado, novoTipo, "NovoTexto"));
        }*/
    }

    private void deleteProcessedMessage(String queueUrl, Message message) {
        sqsClient.deleteMessage(builder -> builder.queueUrl(queueUrl).receiptHandle(message.receiptHandle()));
    }
}
