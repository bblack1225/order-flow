package com.demo.orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String productId;
    String name;
    int orderQty ;
    int actualQty  ;
    int price;
    String payerName ;
    String phone;
    String email;
    String address;
    String paymentName;
    int paymentStatus;
    String description;
    int status;
}
