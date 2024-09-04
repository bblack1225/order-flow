package com.demo.orderservice.dto;

import com.demo.orderservice.entity.OrderInformation;
import lombok.Data;

@Data
public class ReceiveOrderDto {
    int orderId;
    int actualQty;
    int status;
    String description;

    public static ReceiveOrderDto convertDto(OrderInformation request){
        ReceiveOrderDto response = new ReceiveOrderDto();
        response.setOrderId(request.getId());
        response.setActualQty(request.getActualQty());
        response.setStatus(request.getStatus());
        response.setDescription(request.getDescription());
        return response;
    }
}
