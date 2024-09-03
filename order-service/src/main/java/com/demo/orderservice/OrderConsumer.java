package com.demo.orderservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @RabbitListener(queues = "order.create.queue")
    public void receiveMessage(String message) {
        System.out.println("Message received: " + message);
    }
}
