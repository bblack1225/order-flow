CREATE DATABASE IF NOT EXISTS orders;

CREATE TABLE orders.order_information (
                                          id INT auto_increment PRIMARY KEY,
                                          product_id NVARCHAR(255) NOT NULL,
                                          name NVARCHAR(255) NOT NULL,
                                          order_qty INT NOT NULL,
                                          actual_qty INT NOT NULL,
                                          price INT NOT NULL,
                                          payer_name NVARCHAR(255) NOT NULL,
                                          phone NVARCHAR(50),
                                          email NVARCHAR(255),
                                          address NVARCHAR(255),
                                          payment_name NVARCHAR(100),
                                          payment_status INT,
                                          description NVARCHAR(255),
                                          status INT NOT NULL
);
