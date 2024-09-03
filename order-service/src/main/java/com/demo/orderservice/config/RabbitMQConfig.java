package com.demo.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

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