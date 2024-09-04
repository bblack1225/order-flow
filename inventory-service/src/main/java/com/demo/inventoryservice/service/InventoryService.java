package com.demo.inventoryservice.service;

import com.demo.inventoryservice.message.CreateOrderMessage;

public interface InventoryService {
    void updateInventory(CreateOrderMessage message);
}
