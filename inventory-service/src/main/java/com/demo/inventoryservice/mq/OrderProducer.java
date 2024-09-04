package com.demo.inventoryservice.mq;

import com.demo.inventoryservice.message.OrderStatusMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;


    public void sendOrderStatusMessage(OrderStatusMessage message) {
        rabbitTemplate.convertAndSend("order.exchange", "order.status.key", message);
    }
}
