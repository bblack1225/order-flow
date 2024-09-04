package com.demo.orderservice.dto;

import lombok.Data;


@Data
public class OrderStatusMessage {
    private String description;
    private int status;
    private int orderId;
    private int actualQty;
}
