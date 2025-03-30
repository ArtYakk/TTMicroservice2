package com.artemyakkonen.spring.boot.ttmicroservice2.service;

import com.artemyakkonen.spring.boot.ttmicroservice2.dto.MessageDTO;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Message;
import com.artemyakkonen.spring.boot.ttmicroservice2.mapper.MessageMapper;
import com.artemyakkonen.spring.boot.ttmicroservice2.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository  = messageRepository;
    }

    public List<MessageDTO> getMessagesByUserId(Long userId) {
        return MessageMapper.toMessageDTOList(messageRepository.findByUser_Id(userId));
    }

    public MessageDTO getMessageById(Long userId) {
        Message message = messageRepository.findById(userId).orElse(null);
        if (message != null) {
            return MessageMapper.toMessageDTO(message);
        }
        return null;
    }

    public void deleteMessageById(Long messageId) {
        messageRepository.deleteById(messageId);
    }



}
