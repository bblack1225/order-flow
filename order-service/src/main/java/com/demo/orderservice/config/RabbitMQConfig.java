package com.demo.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue orderQueueCreate() {
        return QueueBuilder.durable("order.create.queue").build();
    }

    @Bean
    public Queue orderQueueStatus() {
        return QueueBuilder.durable("order.status.queue").build();
    }

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange("order.exchange");
    }

    @Bean
    public Binding bindingOrderQueueCreate(Queue orderQueueCreate, TopicExchange orderExchange) {
        return BindingBuilder.bind(orderQueueCreate).to(orderExchange).with("order.create.key");
    }

    @Bean
    public Binding bindingOrderQueueStatus(Queue orderQueueStatus, TopicExchange orderExchange) {
        return BindingBuilder.bind(orderQueueStatus).to(orderExchange).with("order.status.key");
    }
}