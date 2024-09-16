package com.demo.orderservice.controller;

import com.demo.orderservice.dto.OrderDto;
import com.demo.orderservice.entity.OrderInformation;
import com.demo.orderservice.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Resource
    OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderInformation>> getOrders() {
        List<OrderInformation> list = orderService.getAllOrder();

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<String> saveOrder(@RequestBody OrderDto request) {
        orderService.sendOrder(request);

        return ResponseEntity.ok("Order saved successfully");
    }

    @PostMapping("/modify")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDto request) {
        orderService.updateOrder(request);

        return ResponseEntity.ok("Order updated successfully");
    }

    @GetMapping("/check/{orderId}")
    public ResponseEntity<String> checkOrder(@PathVariable int orderId) {
        String response = orderService.validOrderByStatus(orderId);

        return ResponseEntity.ok(response);
    }
}
