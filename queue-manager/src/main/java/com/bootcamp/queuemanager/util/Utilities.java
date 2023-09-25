package com.bootcamp.queuemanager.util;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.services.sqs.model.Message;

public class Utilities {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static CustomerFeedbackDTO messageToDTO(Message message) throws JsonProcessingException {
        String messageBody = message.body();
        JsonNode jsonNode = objectMapper.readTree(messageBody).get("Message");
        String strMessage = objectMapper.writeValueAsString(jsonNode);
        CustomerFeedbackDTO customerFeedbackDTO = objectMapper.readValue(strMessage, CustomerFeedbackDTO.class);


        //CustomerFeedbackDTO customerFeedbackDTO = objectMapper.readValue(messageBody, CustomerFeedbackDTO.class);
        //String topicArn = "customerFeedbackDTO.getTopicArn()";
        //customerFeedbackDTO.setType(getTypeFromArn(topicArn));
        return customerFeedbackDTO;
    }
    private static Type getTypeFromArn(String arn){
        if (arn.contains(Type.SUGESTAO.name().toLowerCase())) return Type.SUGESTAO;
        if (arn.contains(Type.ELOGIO.name().toLowerCase())) return Type.ELOGIO;
        if (arn.contains(Type.CRITICA.name().toLowerCase())) return Type.CRITICA;
        return null;
    }
}
