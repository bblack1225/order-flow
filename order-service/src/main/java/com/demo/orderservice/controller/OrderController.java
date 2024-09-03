package com.demo.orderservice.controller;

import com.demo.orderservice.dto.OrderDto;
import com.demo.orderservice.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Resource
    OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<String> getOrders() {
        orderService.getAllOrder();
        return ResponseEntity.ok("Orders");
    }

    @PostMapping
    public ResponseEntity<String> saveOrder(@RequestBody OrderDto request) {
        orderService.sendOrder("order.exchange", "order.create.key", request);
        return ResponseEntity.ok("Order placed successfully");
    }
}
