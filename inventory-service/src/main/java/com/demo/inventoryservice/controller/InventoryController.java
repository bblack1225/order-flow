package com.demo.inventoryservice.controller;

import com.demo.inventoryservice.entity.Product;
import com.demo.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final ProductRepository productRepository;

    @GetMapping("/{id}")
    public String getById(@PathVariable  String id) {
        Product product = productRepository.findByName("iPhone11");
        log.info("Product: {}", product.getId());
        return "Inventory with id: " + product.getId();
    }
}
