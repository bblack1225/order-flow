package com.demo.inventoryservice.service;


import com.demo.commonutil.message.CreateOrderMessage;

public interface InventoryService {
    void updateInventory(CreateOrderMessage message);
}
