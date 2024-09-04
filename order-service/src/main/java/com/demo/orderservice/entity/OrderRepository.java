package com.demo.orderservice.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository  extends JpaRepository<OrderInformation, Integer> {

}
