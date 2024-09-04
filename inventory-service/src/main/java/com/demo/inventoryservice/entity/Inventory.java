package com.demo.inventoryservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "inventory")
public class Inventory {
    @Id
    private String id;
    private String productId;
    private int quantity;
    private String status;
    private LocalDateTime lastUpdated;
}
