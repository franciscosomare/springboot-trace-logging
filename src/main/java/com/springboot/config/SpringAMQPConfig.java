package com.springboot.config;

import com.springboot.message.MessageConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class SpringAMQPConfig {

    public static final String QUEUENAME = "queue-test";
    public static final String EXCHANGENAME = "queue-test-exchange";
    public static final String ROUTINGKEY = "qt";

    @Bean
    Queue queue() {
        return new Queue(QUEUENAME, true);
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGENAME);
    }

    @Bean
    Binding binding1(@Qualifier("queue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY);
    }

    @Bean
    SimpleMessageListenerContainer springAmqpContainer(ConnectionFactory connectionFactory,
                                                       MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUENAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageConsumer messageReceiver) {
        return new MessageListenerAdapter(messageReceiver, "receiveMessage");
    }
}