package com.demo.orderservice.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<OrderInformation, String> {

}
