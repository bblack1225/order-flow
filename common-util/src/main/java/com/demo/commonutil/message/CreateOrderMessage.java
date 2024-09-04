package com.demo.commonutil.message;

import lombok.Data;

@Data
public class CreateOrderMessage {
    private String productId;
    private int orderQty;
    private int orderId;
}
