package com.demo.inventoryservice.repository;

import com.demo.inventoryservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByName(String name);
}
