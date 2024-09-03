package com.demo.orderservice.service;

import com.demo.orderservice.dto.OrderDto;
import com.demo.orderservice.entity.OrderInformation;
import com.demo.orderservice.entity.OrderRepository;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Resource
    OrderRepository orderRepository;
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void getAllOrder() {
        List<OrderInformation> orderList = orderRepository.findAll();
        System.out.println(orderList);
    }

    public void saveOrder(OrderInformation orderInformation) {
        orderRepository.save(orderInformation);
    }

    public void sendOrder(String exchange, String routingKey, OrderDto orderDto) {
        OrderInformation entity = OrderDto.convertDto(orderDto);
        saveOrder(entity);

        rabbitTemplate.convertAndSend(exchange, routingKey, orderDto.toString());
        System.out.println("Message has been sent: " + orderDto);
    }
}
