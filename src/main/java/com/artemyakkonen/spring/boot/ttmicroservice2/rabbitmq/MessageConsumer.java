package com.artemyakkonen.spring.boot.ttmicroservice2.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "myQueue")
public class MessageConsumer {

    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("Received \n" + message);
    }
}
