package com.demo.orderservice.dto;

import com.demo.orderservice.entity.OrderInformation;
import lombok.Data;

@Data
public class SendOrderDto {
    int orderId;
    String productId;
    int orderQty;

    public static SendOrderDto convertDto(OrderInformation request){
        SendOrderDto response = new SendOrderDto();
        response.setOrderId(request.getId());
        response.setProductId(request.getProductId());
        response.setOrderQty(request.getActualQty());
        return response;
    }
}
