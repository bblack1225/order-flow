package com.demo.inventoryservice.mq;

import com.demo.commonutil.message.OrderStatusMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryProducer {

    private final RabbitTemplate rabbitTemplate;


    public void sendOrderStatusMessage(OrderStatusMessage message) {
        log.info("Sending order status message to order service: {}", message);
        rabbitTemplate.convertAndSend("order.exchange", "order.status.key", message);
    }
}
