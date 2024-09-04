package com.demo.orderservice.dto;

import com.demo.orderservice.entity.OrderInformation;
import lombok.Data;

@Data
public class CreateOrderMessage {
    private String productId;
    private int orderQty;
    private int orderId;

    public static CreateOrderMessage convertDto(OrderInformation request){
        CreateOrderMessage response = new CreateOrderMessage();
        response.setOrderId(request.getId());
        response.setProductId(request.getProductId());
        response.setOrderQty(request.getOrderQty());
        return response;
    }
}
