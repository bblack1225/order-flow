package com.demo.orderservice.service;

import com.demo.commonutil.message.CreateOrderMessage;
import com.demo.commonutil.message.OrderStatusMessage;
import com.demo.orderservice.dto.*;
import com.demo.orderservice.entity.OrderInformation;
import com.demo.orderservice.entity.OrderRepository;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    public OrderInformation findOrderById(int id) {
        return orderRepository.findById(id).orElse(new OrderInformation());
    }

    public OrderInformation saveOrder(OrderInformation orderInformation) {
        System.out.println("Order saved: " + orderInformation);
       return orderRepository.save(orderInformation);
    }

    public void sendOrder(String exchange, String routingKey, OrderDto orderDto) {
        OrderInformation orderInformation = OrderDto.convertDto(orderDto);
        OrderInformation entity= saveOrder(orderInformation);
        CreateOrderMessage response = convertToCreateOrderMsg(entity);

        rabbitTemplate.convertAndSend(exchange, routingKey, response);
        System.out.println("Message has been sent: " + response);
    }

    @RabbitListener(queues = "order.status.queue")
    public void receiveOrderMessage(OrderStatusMessage dto) {
        System.out.println("1:  " + dto);
        saveOrderByActualQty(dto);

        System.out.println("Received inventory-service: " + dto);
    }
    private void saveOrderByActualQty(OrderStatusMessage dto) {
        OrderInformation entity = findOrderById(dto.getOrderId());
        entity.setActualQty(dto.getActualQty());
        System.out.println("2:  " + entity);

        saveOrder(entity);
    }

    private CreateOrderMessage convertToCreateOrderMsg(OrderInformation request){
        CreateOrderMessage response = new CreateOrderMessage();
        response.setOrderId(request.getId());
        response.setProductId(request.getProductId());
        response.setOrderQty(request.getOrderQty());
        return response;
    }
}
