package com.markfy.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class QueueSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${queue.name.producer}")
    private String queueProducerName;

   public void send(String order){
       rabbitTemplate.convertAndSend(queueProducerName, order);
   }

}
