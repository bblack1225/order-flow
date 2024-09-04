package com.demo.orderservice.mq;

import com.demo.commonutil.message.CreateOrderMessage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendOrderMessage(CreateOrderMessage message) {
        rabbitTemplate.convertAndSend("order.exchange", "order.create.key", message);
    }
}
