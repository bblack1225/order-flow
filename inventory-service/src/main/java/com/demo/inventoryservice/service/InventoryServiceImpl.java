package com.demo.inventoryservice.service;

import com.demo.commonutil.message.CreateOrderMessage;
import com.demo.commonutil.message.OrderStatusMessage;
import com.demo.inventoryservice.entity.Inventory;
import com.demo.inventoryservice.entity.InventoryHistory;
import com.demo.inventoryservice.mq.InventoryProducer;
import com.demo.inventoryservice.repository.InventoryHistoryRepository;
import com.demo.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryHistoryRepository inventoryHistoryRepository;
    private final MongoTemplate mongoTemplate;
    private final InventoryProducer inventoryProducer;
    private static final int SUCCESS_STATUS = 1;
    private static final int FAILED_STATUS = 0;

    /**
     * 1. 檢查商品是否存在
     * 2. 檢查庫存是否足夠
     * 3. 更新庫存，但要確定更新是否成功，因為有可能同時有多個訂單在更新庫存
     * @param message
     */
    @Override
    public void updateInventory(CreateOrderMessage message) {
        int orderQty = message.getOrderQty();
        int orderId = message.getOrderId();
        String productId = message.getProductId();
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if(inventory == null){
            log.error("Inventory not found for product id: {}", productId);
            sendOrderStatusMsg("Product Not Found", orderId, FAILED_STATUS, 0);
            return;
        }

        Query query = new Query(Criteria.where("productId").is(productId)
                .and("quantity").gte(orderQty));
        Update update = new Update().inc("quantity", -orderQty);
        Inventory updatedInventory = mongoTemplate.findAndModify(query, update, Inventory.class);
        if(updatedInventory == null){
            int latestQty = getActualQty(productId);
            log.error("Inventory not available or insufficient for product id: {}", productId);
            sendOrderStatusMsg("Inventory Not Available", orderId, FAILED_STATUS, latestQty);
            return;
        }
        
        log.info("Inventory updated successfully for product id: {}", productId);
        sendOrderStatusMsg("Inventory Updated Successfully", orderId, SUCCESS_STATUS, orderQty);
        InventoryHistory history = new InventoryHistory();
        history.setProductId(productId);
        history.setQuantity(orderQty);
        history.setOrderId(orderId);
        history.setAction("CREATE_ORDER");
        history.setCreatedAt(LocalDateTime.now());
        inventoryHistoryRepository.save(history);
    }

    private int getActualQty(String productId) {

        Inventory inventory = inventoryRepository.findByProductId(productId);
        return inventory != null ? inventory.getQuantity() : 0;
    }

    private void sendOrderStatusMsg(String description, int orderId, int status, int actualQty) {
        OrderStatusMessage orderStatusMessage = new OrderStatusMessage();
        orderStatusMessage.setOrderId(orderId);
        orderStatusMessage.setDescription(description);
        orderStatusMessage.setStatus(status);
        orderStatusMessage.setActualQty(actualQty);
        inventoryProducer.sendOrderStatusMessage(orderStatusMessage);
    }
}
