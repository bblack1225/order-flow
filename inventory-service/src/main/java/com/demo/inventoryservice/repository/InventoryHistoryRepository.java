package com.demo.inventoryservice.repository;

import com.demo.inventoryservice.entity.InventoryHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryHistoryRepository extends MongoRepository<InventoryHistory, String> {
}
