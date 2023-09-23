package com.bootcamp.queuemanager.util;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import software.amazon.awssdk.services.sqs.model.Message;

public class Utilities {
    public static CustomerFeedbackDTO messageToDTO(Message message) {
        String messageId = message.messageId();
        String messageContent = message.getValueForField("Message", String.class).toString();
        String messageType = getTypeFromArn(message.getValueForField("TopicArn", String.class).toString());

        return new CustomerFeedbackDTO(messageId, null, Type.valueOf(messageType), messageContent);
    }
    private static String getTypeFromArn(String arn){
        if (arn.contains(Type.SUGESTAO.name())) return Type.SUGESTAO.name();
        if (arn.contains(Type.ELOGIO.name())) return Type.ELOGIO.name();
        if (arn.contains(Type.CRITICA.name())) return Type.CRITICA.name();
        return null;
    }
}
