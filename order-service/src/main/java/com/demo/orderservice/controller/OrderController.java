package com.demo.orderservice.controller;

import com.demo.orderservice.dto.OrderDto;
import com.demo.orderservice.service.OrderService;
import jakarta.annotation.Resource;
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
        orderService.sendOrder(request);

        return ResponseEntity.ok("Order placed successfully");
    }

    @GetMapping("/check/{orderId}")
    public ResponseEntity<String> checkOrder(@PathVariable int orderId) {
        String response = orderService.validOrderByStatus(orderId);
        return ResponseEntity.ok(response);
    }
}
