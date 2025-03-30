package com.artemyakkonen.spring.boot.ttmicroservice2.rabbitmq;

import com.artemyakkonen.spring.boot.ttmicroservice2.dto.ActivityDTO;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.MessageDTO;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.UserDTO;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.rabbit.RabbitMessageDTO;


import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Activity;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Message;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.User;
import com.artemyakkonen.spring.boot.ttmicroservice2.mapper.UserMapper;
import com.artemyakkonen.spring.boot.ttmicroservice2.service.UserService;
import com.artemyakkonen.spring.boot.ttmicroservice2.util.AnsiColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class MessageConsumerService {

    private final UserService userService; // Сервис для работы с пользователями

    @Autowired
    public MessageConsumerService(UserService userService) {
        this.userService = userService;
    }


    @RabbitListener(queues = "myQueue")
    public void receiveMessage(RabbitMessageDTO rabbitMessage) {
        log.info(AnsiColors.blackOnBlue("Received: " + rabbitMessage.getBody()));
        Message message = Message.builder()
                .body(rabbitMessage.getBody())
                .time(rabbitMessage.getTimestamp())
                .build();
        Activity activity = Activity.builder()
                .time(rabbitMessage.getTimestamp())
                .type("SEND_STATUS")
                .build();

        User user = UserMapper.toUser(userService.getUserByUuid(rabbitMessage.getUuid()));

        if (user == null) {
            user = User.builder()
                    .uuid(rabbitMessage.getUuid())
                    .role("USER")
                    .messages(new ArrayList<>(List.of(message)))
                    .activities(new ArrayList<>(List.of(activity)))
                    .build();
            URI locationOfNewUser = userService.addUser(user);
            log.info(AnsiColors.blackOnBlue("Added user on: " + locationOfNewUser));
        }else{
            user.addActivity(activity);
            user.addMessage(message);
            userService.addUser(user);
        }

    }

}
