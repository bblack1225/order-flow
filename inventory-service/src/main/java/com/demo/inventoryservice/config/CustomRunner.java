package com.demo.inventoryservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequiredArgsConstructor
public class CustomRunner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    @Override
    public void run(String... args) throws Exception {
    rabbitTemplate.convertAndSend("order.exchange", "order.created", "order message");
    }
}
