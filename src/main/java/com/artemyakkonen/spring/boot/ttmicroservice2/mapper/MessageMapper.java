package com.artemyakkonen.spring.boot.ttmicroservice2.mapper;

import com.artemyakkonen.spring.boot.ttmicroservice2.dto.MessageDTO;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Message;

import java.util.List;
import java.util.stream.Collectors;

public class MessageMapper {

    public static MessageDTO toMessageDTO(Message message) {
        if(message == null) {return null;}
        return MessageDTO.builder()
                .id(message.getId())
                .body(message.getBody())
                .time(message.getTime())
                .build();
    }

    public static Message toMessage(MessageDTO messageDTO) {
        if(messageDTO == null) {return null;}
        return Message.builder()
                .id(messageDTO.getId())
                .body(messageDTO.getBody())
                .time(messageDTO.getTime())
                .build();
    }

    public static List<MessageDTO> toMessageDTOList(List<Message> messageList) {
        return messageList.stream().map(MessageMapper::toMessageDTO).collect(Collectors.toList());
    }

    public static List<Message> toMessageList(List<MessageDTO> messageDTOList) {
        return messageDTOList.stream().map(MessageMapper::toMessage).collect(Collectors.toList());
    }
}
