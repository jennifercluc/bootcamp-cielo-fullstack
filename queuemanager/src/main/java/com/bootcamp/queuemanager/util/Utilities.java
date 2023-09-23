package com.bootcamp.queuemanager.util;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import software.amazon.awssdk.services.sqs.model.Message;

public class Utilities {
    public static CustomerFeedbackDTO messageToDTO(Message message) {
        String messageId = message.messageId();
        String messageContent = message.getValueForField("Message", String.class).toString();
        String topicArn = message.getValueForField("TopicArn", String.class).toString();
        String messageType = getTypeFromArn(topicArn);

        return new CustomerFeedbackDTO(messageId, Status.RECEBIDO, Type.valueOf(messageType), messageContent, topicArn);
    }
    private static String getTypeFromArn(String arn){
        if (arn.contains(Type.SUGESTAO.name())) return Type.SUGESTAO.name();
        if (arn.contains(Type.ELOGIO.name())) return Type.ELOGIO.name();
        if (arn.contains(Type.CRITICA.name())) return Type.CRITICA.name();
        return null;
    }
}
