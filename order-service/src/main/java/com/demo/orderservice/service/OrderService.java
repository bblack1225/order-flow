package com.demo.orderservice.service;

import com.demo.commonutil.message.CreateOrderMessage;
import com.demo.commonutil.message.OrderStatusMessage;
import com.demo.orderservice.dto.OrderDto;
import com.demo.orderservice.entity.OrderInformation;
import com.demo.orderservice.entity.OrderRepository;
import com.demo.orderservice.mq.OrderProducer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {
    @Resource
    OrderRepository orderRepository;
    @Resource
    private OrderProducer orderProducer;

    public List<OrderInformation> getAllOrder() {
        return orderRepository.findAll();
    }

    public OrderInformation findOrderById(int id) {
        Optional<OrderInformation> entity = orderRepository.findById(id);
        System.out.println(entity.orElse(new OrderInformation()));
        return entity.orElse(new OrderInformation());
    }

    public OrderInformation saveOrder(OrderInformation orderInformation) {
        return orderRepository.saveAndFlush(orderInformation);
    }

    public void sendOrder(OrderDto orderDto) {
        OrderInformation orderInformation = OrderDto.convertDto(orderDto);
        OrderInformation entity = saveOrder(orderInformation);
        CreateOrderMessage response = convertToCreateOrderMsg(entity);

        orderProducer.sendOrderMessage(response);
        log.info("Message has been sent: " + response);
    }

    private CreateOrderMessage convertToCreateOrderMsg(OrderInformation request) {
        CreateOrderMessage response = new CreateOrderMessage();
        response.setOrderId(request.getId());
        response.setProductId(request.getProductId());
        response.setOrderQty(request.getOrderQty());
        return response;
    }

    public void updateOrder(OrderDto orderDto) {
        OrderInformation entity = findOrderById(orderDto.getId());
        entity.setDescription(orderDto.getDescription());

        saveOrder(entity);
    }

    public void saveOrderByActualQty(OrderStatusMessage dto) {
        OrderInformation entity = findOrderById(dto.getOrderId());
        entity.setActualQty(dto.getActualQty());
        entity.setStatus(dto.getStatus());
        entity.setDescription(dto.getDescription());
        log.info("saveOrderByActualQty: " + entity);

        saveOrder(entity);
    }

    public String validOrderByStatus(int orderId) {
        OrderInformation orderInformation = findOrderById(orderId);

        return orderInformation.getStatus() == 0 ? "Order is failed" : "Order is successfully";
    }
}
