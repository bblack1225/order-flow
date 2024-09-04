package com.demo.orderservice.mq;

import com.demo.commonutil.message.OrderStatusMessage;
import com.demo.orderservice.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderConsumer {
    @Resource
    OrderService orderService;

    @RabbitListener(queues = "order.status.queue")
    public void receiveOrderMessage(OrderStatusMessage dto) {
        orderService.saveOrderByActualQty(dto);
        log.info("Received inventory-service: " + dto);
    }
}
