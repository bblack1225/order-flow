package com.demo.orderservice.dto;

import com.demo.orderservice.entity.OrderInformation;
import lombok.Data;

@Data
public class SendOrderDto {
    int orderId;
    int productId;
    int actualQty;

    public static SendOrderDto convertDto(OrderInformation request){
        SendOrderDto response = new SendOrderDto();
        response.setOrderId(request.getId());
        response.setProductId(request.getProductId());
        response.setActualQty(request.getActualQty());
        return response;
    }
}
