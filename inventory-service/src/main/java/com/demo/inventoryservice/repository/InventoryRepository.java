package com.demo.inventoryservice.repository;

import com.demo.inventoryservice.entity.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    Inventory findByProductId(String productId);
}
