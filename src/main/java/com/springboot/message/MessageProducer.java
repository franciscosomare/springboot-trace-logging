package com.springboot.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.config.SpringAMQPConfig;


@Component
public class MessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info("Producer: {}", message);
        rabbitTemplate.convertAndSend(SpringAMQPConfig.EXCHANGENAME, SpringAMQPConfig.ROUTINGKEY, message);
    }
}