package com.demo.orderservice.dto;

import com.demo.orderservice.entity.OrderInformation;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;

@Data
public class OrderDto {
    int id;
    String productId;
    String name;
    int orderQty ;
    int actualQty  ;
    int price;
    String payerName ;
    String phone;
    String email;
    String address;
    String paymentName;
    int paymentStatus;
    String description;
    int status;

    public static OrderInformation convertDto(OrderDto orderDto){
        OrderInformation response = new OrderInformation();
        response.setId(orderDto.getId());
        response.setProductId(orderDto.getProductId());
        response.setName(orderDto.getName());
        response.setOrderQty(orderDto.getOrderQty());
        if(!ObjectUtils.isEmpty(orderDto.getActualQty()))
            response.setActualQty(orderDto.getActualQty());
        response.setPrice(orderDto.getPrice());
        response.setPayerName(orderDto.getPayerName());
        response.setPhone(orderDto.getPhone());
        response.setEmail(orderDto.getEmail());
        response.setAddress(orderDto.getAddress());
        response.setPaymentName(orderDto.getPaymentName());
        response.setPaymentStatus(orderDto.getPaymentStatus());
        response.setDescription(orderDto.getDescription());
        response.setStatus(orderDto.getStatus());

        return response;
    }
}
