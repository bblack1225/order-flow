package com.demo.inventoryservice.mq;

import com.demo.inventoryservice.message.CreateOrderMessage;
import com.demo.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryConsumer {

    private final InventoryService inventoryService;

    @RabbitListener(queues = "order.create.queue")
    public void getMessage(CreateOrderMessage message){
        log.info("Create order message received: {}", message);
        inventoryService.updateInventory(message);
    }
}
