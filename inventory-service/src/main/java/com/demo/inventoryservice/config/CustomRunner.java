package com.demo.inventoryservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomRunner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    @Override
    public void run(String... args) throws Exception {
//        CreateOrderMessage message = new CreateOrderMessage();
//        message.setOrderQty(2);
//        message.setProductId("66d675ab3b8b2b437c89f369");
//        message.setOrderId(1);
//    rabbitTemplate.convertAndSend("order.exchange", "order.create.key", message);
    }
}
