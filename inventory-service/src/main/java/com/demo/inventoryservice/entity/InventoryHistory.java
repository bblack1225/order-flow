package com.demo.inventoryservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "inventory_history")
public class InventoryHistory {
    @Id
    private String id;
    private String productId;
    private int quantity;
    private int orderId;
    private String action;
    private LocalDateTime createdAt;
}
