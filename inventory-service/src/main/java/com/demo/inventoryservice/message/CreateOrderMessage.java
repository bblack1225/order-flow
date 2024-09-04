package com.demo.inventoryservice.message;

import lombok.Data;

@Data
public class CreateOrderMessage {
    private String productId;
    private int orderQty;
    private int orderId;
}
