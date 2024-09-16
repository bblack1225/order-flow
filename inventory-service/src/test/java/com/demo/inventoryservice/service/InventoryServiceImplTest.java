package com.demo.inventoryservice.service;

import com.demo.commonutil.message.CreateOrderMessage;
import com.demo.commonutil.message.OrderStatusMessage;
import com.demo.inventoryservice.entity.Inventory;
import com.demo.inventoryservice.entity.InventoryHistory;
import com.demo.inventoryservice.mq.InventoryProducer;
import com.demo.inventoryservice.repository.InventoryHistoryRepository;
import com.demo.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;
    @Mock
    private InventoryHistoryRepository inventoryHistoryRepository;
    @Mock
    private MongoTemplate mongoTemplate;
    @Mock
    private InventoryProducer inventoryProducer;

    @InjectMocks
    private InventoryServiceImpl inventoryService;
    private static final Integer SUCCESS_STATUS = 1;
    private static final Integer FAILED_STATUS = 0;

    @Test
    void testUpdateInventorySuccess(){
        String productId = "123";
        int orderId = 1;
        int orderQty = 10;
        int actualQty = 20;
        CreateOrderMessage message = new CreateOrderMessage();
        message.setProductId(productId);
        message.setOrderId(orderId);
        message.setOrderQty(orderQty);

        Inventory inventory = new Inventory();
        inventory.setId("1");
        inventory.setProductId(productId);
        inventory.setQuantity(actualQty);
        inventory.setStatus("available");
        inventory.setLastUpdated(LocalDateTime.now());

        Inventory updatedInventory = new Inventory();
        updatedInventory.setId("1");
        updatedInventory.setProductId(productId);
        updatedInventory.setQuantity(actualQty - orderQty);
        updatedInventory.setStatus("available");
        updatedInventory.setLastUpdated(LocalDateTime.now());


        when(inventoryRepository.findByProductId(productId)).thenReturn(inventory);
        when(mongoTemplate.findAndModify(any(Query.class), any(Update.class), eq(Inventory.class)))
                .thenReturn(updatedInventory);

        when(inventoryHistoryRepository.save(any())).thenReturn(any(InventoryHistory.class));

        inventoryService.updateInventory(message);

        ArgumentCaptor<OrderStatusMessage> messageCaptor = ArgumentCaptor.forClass(OrderStatusMessage.class);
        verify(inventoryHistoryRepository, times(1)).save(any(InventoryHistory.class));
        verify(inventoryProducer, times(1)).sendOrderStatusMessage(messageCaptor.capture());
        assertEquals("Inventory Updated Successfully", messageCaptor.getValue().getDescription());
        assertEquals(orderId, messageCaptor.getValue().getOrderId());
        assertEquals(SUCCESS_STATUS, messageCaptor.getValue().getStatus());
        assertEquals(orderQty, messageCaptor.getValue().getActualQty());
    }

    @Test
    void testProductNotFoundError() {
        String productId = "123";
        int orderId = 1;
        int orderQty = 10;
        CreateOrderMessage message = new CreateOrderMessage();
        message.setProductId(productId);
        message.setOrderId(orderId);
        message.setOrderQty(orderQty);

        inventoryService.updateInventory(message);

        ArgumentCaptor<OrderStatusMessage> messageCaptor = ArgumentCaptor.forClass(OrderStatusMessage.class);


        verify(inventoryProducer, times(1))
                .sendOrderStatusMessage(messageCaptor.capture());
        assertEquals("Product Not Found", messageCaptor.getValue().getDescription());
        assertEquals(orderId, messageCaptor.getValue().getOrderId());
        assertEquals(FAILED_STATUS, messageCaptor.getValue().getStatus());
        assertEquals(0, messageCaptor.getValue().getActualQty());
    }

    @Test
    void testQuantityNotAvailable() {
        String productId = "123";
        int orderId = 1;
        int orderQty = 10;
        int actualQty = 8;
        CreateOrderMessage message = new CreateOrderMessage();
        message.setProductId(productId);
        message.setOrderId(orderId);
        message.setOrderQty(orderQty);

        Inventory inventory = new Inventory();
        inventory.setId("1");
        inventory.setProductId(productId);
        inventory.setQuantity(actualQty);
        inventory.setStatus("available");
        inventory.setLastUpdated(LocalDateTime.now());

        when(inventoryRepository.findByProductId(productId)).thenReturn(inventory);

        inventoryService.updateInventory(message);

        ArgumentCaptor<OrderStatusMessage> messageCaptor = ArgumentCaptor.forClass(OrderStatusMessage.class);

        verify(inventoryProducer, times(1))
                .sendOrderStatusMessage(messageCaptor.capture());
        assertEquals("Inventory Not Available", messageCaptor.getValue().getDescription());
        assertEquals(orderId, messageCaptor.getValue().getOrderId());
        assertEquals(FAILED_STATUS, messageCaptor.getValue().getStatus());
        assertEquals(actualQty, messageCaptor.getValue().getActualQty());
    }
}