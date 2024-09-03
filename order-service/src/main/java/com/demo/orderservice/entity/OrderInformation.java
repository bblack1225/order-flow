package com.demo.orderservice.entity;

import jakarta.persistence.Entity;
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
    int id;
    String name;
    int orderQty ;
    int actualQty  ;
    int price;
    String payerName ;
    String phone;
    String email;
    String address;
    String paymentName;
    String paymentStatus;
    int status;
}
