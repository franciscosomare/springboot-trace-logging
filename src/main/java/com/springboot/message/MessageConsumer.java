package com.springboot.message;

import com.springboot.config.SpringAMQPConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @Value("${spring.application.name}")
    private String servicio;

    @RabbitListener(queues = { SpringAMQPConfig.QUEUENAME })
    public void receiveMessage(String message) {
        logger.info("Received Message: {}", message);
    }
}